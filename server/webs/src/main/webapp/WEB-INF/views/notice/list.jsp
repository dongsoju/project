<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
table tr:hover { cursor: pointer;  background-color: #f6f6f6 }

	}


	.mainsm{
		display:flex;
		container: text-center;
	}

	.smdd{
		text-align: center; margin: 0 auto;
		width: auto;
		position: relative;
	}
	
	.nodi{
		display: flex;
		position: relative;
	}

	.serch{
		position: absolute;
		left: 10ox;
		bottom: 0px;
		right: 10px;
	}
	
	.noti{
		text-align: center; margin: 0 auto;s
	}
	
	


	.no1{
		container: text-center;
		text-align: center; margin: 0 auto;s
	}
	
	
	
</style>
 <body>


<div class="mainsm">

<div class='smdd'>

<tbody>

<body>
<h3>공지글목록</h3>


<div class="no">

<form method='post' action='list.no' class="noti">
<div id='list-top' class='w-px1200 no1'>
<div class=" nodi">
<ul style="padding-left: 0">
	<li><select name='search' class='w-px100'>
		<option value='all' ${page.search eq 'all' ? 'selected': ''}>전체</option>
		<option value='title' ${page.search eq 'title' ? 'selected': ''}>제목</option>
		<option value='content' ${page.search eq 'content' ? 'selected': ''}>내용</option>
		<option value='writer' ${page.search eq 'writer' ? 'selected': ''}>작성자</option>
		<option value='t_c' ${page.search eq 't_c' ? 'selected': ''}>제목+내용</option>
		</select>
	</li>
	<li><input type='text' name='keyword' value='${page.keyword}' class='w-px250'></li>
	<li><a class='btn-fill' onclick='$("form").submit()'>검색</a></li>
</ul>
<ul class="serch">
	<!-- 관리자회원으로 로그인한 경우만 새글쓰기 권한 있음 -->
	<c:if test='${loginInfo.admin eq "Y"}'>
	<li><a class='btn-fill' href='regist.no'>새글쓰기</a></li>
	</c:if>
</ul>
</div>
<input type='hidden' name='curPage' value='1'>
</form>


<table class='w-px1200 tb-list'>

<colgroup>
	<col width='80px'>
	<col>
	<col width='120px'>
	<col width='140px'>
	<col width='100px'>
</colgroup>


<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일자</th>
	<th>첨부파일</th>
</tr>

<c:forEach items='${page.list}' var='vo'>
<tr><td>${vo.no }</td>
	<td class='txt-left'>
		<span style="margin-left:${10*vo.indent}px"></span>
		<c:forEach var="i" begin="1" end="${vo.indent}">
			<c:if test="${i eq vo.indent}">
				<i class="fa-regular fa-comment-dots"></i>
			</c:if>			
		</c:forEach>
		<a href='info.no?search=${page.search}&keyword=${page.keyword}&curPage=${page.curPage}&id=${vo.id}'>${vo.title}</a></td>
	<td>${vo.name}</td>
	<td>${vo.writedate}</td>
	<td><c:if test='${! empty vo.filename}'>
		<i class="font-img-c fa-solid fa-paperclip"></i>
		</c:if>
	</td>
</tr>
</c:forEach>

</table>
</div>
</div>
	
</tbody>
</div>
<div class='btnSet'>
<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>


</body>
</html>



