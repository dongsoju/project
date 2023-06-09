package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class CommonUtility {
	
	
	
	
	//첨부파일 삭제
	public void file_delete(String filepath, HttpServletRequest request) {
		//http://localhost/iot/upload/notice/2023/03/03/e7227d4f-b3cf-48e8-a560-b7dfc755e76d_2-1.훈련운영계획서.hwp
		//--> "d://app/iot/upload/notice/2023/03/03/abc34y-afdl_abc.txt"
		if( filepath != null ) {
			filepath = filepath.replace( 
				appURL(request), "d://app/" + request.getContextPath() );
			File file = new File( filepath );
			if( file.exists() ) file.delete();
		}
	}
	
	//첨부파일 다운로드: 클라이언트에 파일저장
	public boolean fileDownload(String filename, String filepath
						, HttpServletRequest request 
						, HttpServletResponse response) {
		boolean exist = true;
		//http://localhost/iot/upload/notice/2023/03/03/e7227d4f-b3cf-48e8-a560-b7dfc755e76d_2-1.훈련운영계획서.hwp
		//--> "d://app/iot/upload/notice/2023/03/03/abc34y-afdl_abc.txt"
		filepath = filepath.replace( appURL(request)
						, "d://app/" + request.getContextPath() );
		
		//실제 물리적 파일이 존재하면 클라이언트에 저장
		File file = new File( filepath );
		if( file.exists() ) {
			
			String mime = request.getSession().getServletContext()
							.getMimeType( filename );
			response.setContentType(mime);
			
			try {
				filename = URLEncoder.encode(filename, "utf-8");
				response.setHeader("content-disposition"
						, "attachment; filename=" + filename);
				
				ServletOutputStream out = response.getOutputStream();
				FileCopyUtils.copy( new FileInputStream(file) , out);
				out.flush();
				
			}catch(Exception e) {
			}
		}else exist = false;
		return exist;
	}
	
	
	//첨부파일 업로드: 서버에 파일저장
	public String fileUpload(MultipartFile file, String category
						, HttpServletRequest request) {
		// upload/profile/2023/02/28/abc.png
		//D:\Study_Spring\.metadata\..bapps\iot\resources
//		String path = request.getSession().getServletContext()
//				.getRealPath("resources");
		String path = "d://app/" + request.getContextPath();
		
		String upload = "/upload/" + category 
		+ new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		
		//D:\Study_Spring...ot\resources/upload/profile/2023/02/28
		path += upload;
		
		//폴더가 없으면 생성
		File folder = new File( path );
		if( ! folder.exists() ) folder.mkdirs();
		
		//해당 폴더에 첨부한 파일 저장
		//afhlwr234-lajh234-aflj342_abc.png
		String uuid = UUID.randomUUID().toString() + "_" 
						+ file.getOriginalFilename();
		try {
			file.transferTo( new File(path, uuid) );
			
		}catch(Exception e) {
		}
		// http://localhost/iot/upload/profile/2023/02/28/adfa-dfa_abc.png
		return  appURL(request) + upload + "/" + uuid;
	}
	
	
	//실행되고 있는 app의 url
	public String appURL(HttpServletRequest request) {
		//  http://127.0.0.1/iot/naverCallback
		//--> http://127.0.0.1/iot
		return request.getRequestURL().toString()
				.replace( request.getServletPath(), "");
	}
	
	//API 요청
	public String requestAPI( String apiURL ) {
		try {	
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			  BufferedReader br;
			  System.out.print("responseCode="+responseCode);
			  if(responseCode==200) { // 정상 호출
			    br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			  } else {  // 에러 발생
			    br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			  }
			  String inputLine;
			  StringBuffer res = new StringBuffer();
			  while ((inputLine = br.readLine()) != null) {
			    res.append(inputLine);
			  }
			  br.close();
			  if(responseCode==200) {
				  System.out.println( res.toString() );
			  }
			  apiURL = res.toString();
		} catch (Exception e) {
		  System.out.println(e);
		}		
		return apiURL;
	}
	
	//API 요청 - 헤더정보추가
	public String requestAPI( String apiURL, String property ) {
		try {	
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", property);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode="+responseCode);
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if(responseCode==200) {
				System.out.println( res.toString() );
			}
			apiURL = res.toString();
		} catch (Exception e) {
			System.out.println(e);
		}		
		return apiURL;
	}
	
	
	
	
	
	
	
	//암호화에 사용할 솔트 생성
	public String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[16];
				
		random.nextBytes(bytes);
		//16개의 바이트를 16진수로 변환
		StringBuffer salt = new StringBuffer();
		for(byte b : bytes) {
			salt.append( String.format("%02x", b) );
		}
		return salt.toString();
	}
}
