package com.hanul.bteam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.bteam.COMMON.CommonMethod;
import com.hanul.bteam.adapter.BoardrAdapter;
import com.hanul.bteam.adapter.GoneAdapter;
import com.hanul.bteam.dto.BoardDTO;
import com.hanul.bteam.dto.GoneDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView,recycler;
    GoneAdapter adapter_re;
    ArrayList<GoneDTO> dtos_re;
    Button btnAdd;
    MainActivity activity;
    GridView gridView;
    BoardrAdapter adapter;
    ArrayList<BoardDTO> dtos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.home_frag,
                container, false);
        activity = (MainActivity) getActivity();
        dtos_re = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        // recyclerView에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(
                activity, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CommonMethod commonMethod = new CommonMethod();
        commonMethod.setParams("type", "1");
        commonMethod.setParams("ptype", "1");
        commonMethod.setParams("num", "6");
        commonMethod.getData("selectHome", new Callback<String>(){
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){
                    Gson gson = new Gson();
                    dtos_re =  gson.fromJson(response.body(), new TypeToken<ArrayList<GoneDTO>>(){}.getType());
                    for(GoneDTO dto: dtos_re){
                        dto.setLocname(dto.getLocname());
                        dto.setFilepath(dto.getFilepath());
                    }
                    adapter_re = new GoneAdapter(activity.getApplicationContext(), dtos_re,activity);
                    recyclerView.setAdapter(adapter_re);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
//         어댑터 객체 생성
        adapter_re = new
                GoneAdapter(activity.getApplicationContext(), dtos_re,activity);
//        // 만든 어댑터를 리싸이클러뷰에 붙인다
        recyclerView.setAdapter(adapter_re);

        // 중요 : dtos 넘겨줄때 반드시 생성해서 넘겨준다
        dtos = new ArrayList<>();

        recycler = view.findViewById(R.id.recycler);
        GridLayoutManager layout = new GridLayoutManager(activity,1,GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layout);
        // 어댑터 객체를 생성한다
        CommonMethod commonMethod2 = new CommonMethod();
        // 어댑터에 생성한 매소드 addDto를 이용하여 dtos에 데이터를 추가한다
        commonMethod2.setParams("type", "1");
        commonMethod2.setParams("ptype", "3");
        commonMethod2.setParams("num", "10");
        commonMethod2.getData("selectmou", new Callback<String>(){
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Gson gson = new Gson();
                    dtos =  gson.fromJson(response.body(), new TypeToken<ArrayList<BoardDTO>>(){}.getType());
                    for(BoardDTO dto: dtos){
                        dto.setLocname(dto.getLocname());
                        dto.setFilepath(dto.getFilepath());
                    }
                    adapter = new BoardrAdapter(activity.getApplicationContext(), dtos,activity);
                    recycler.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
//        adapter = new BoardrAdapter(activity.getApplicationContext(), dtos,activity);
////        // 5. 만든 어댑터를 리스트뷰에 붙인다
//        recycler.setAdapter(adapter);
        // 리스트뷰는 아이템 클릭 이벤트를 제공해준다
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                BoardDTO dto = (BoardDTO) adapter.getItem(position);
//                Toast.makeText(activity, "" +
//                        "선택 : " + position + "\n산이름 : " + dto.getMountin_name()
//                        , Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }
}
