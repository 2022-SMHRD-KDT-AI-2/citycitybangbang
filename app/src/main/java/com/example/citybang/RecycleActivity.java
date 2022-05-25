package com.example.citybang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {
    List<ContactVO> data;
    RecyclerView rv_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        rv_contact = findViewById(R.id.rv_contact);

        data = new ArrayList<>();
        for (int i = 0; i <5; i++){
            data.add(new ContactVO("지역"+i, "일자" + i, "시간" + i));
        }

        //RecylerView에는 LayoutManager : 레이아웃의 모양을 결정!!
                // LinearLayoutManager 사용해보자
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rv_contact.setLayoutManager(layoutManager);








    }
}