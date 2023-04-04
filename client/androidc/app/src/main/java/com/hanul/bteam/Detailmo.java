package com.hanul.bteam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanul.bteam.dto.GoneDTO;

public class Detailmo extends Fragment {
    MainActivity activity;
    Button course,danger;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = (ViewGroup) inflater.inflate(R.layout.detailmo,
                container, false);
        activity =(MainActivity)getActivity();
        Bundle b = activity.bundle;
        GoneDTO d = (GoneDTO) b.getSerializable("dto");
        TextView t = view.findViewById(R.id.locname);
        t.setText(d.getLocname());
        ImageView i = view.findViewById(R.id.filepath);
        Glide.with(view).load(d.getFilepath()).into(i);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.course);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course fragment = new Course();
                activity.fragmentControl(fragment);
            }
        });

        Button btn1 = view.findViewById(R.id.danger);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Danger fragment = new Danger();
                activity.fragmentControl(fragment);
            }
        });
    }


}