package com.example.citybang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class ReportlistActivity extends AppCompatActivity {

    ListView lv_contact;
    ContactAdapter adapter;
    List<ContactVO> data;

    RequestQueue requestQueue;

    String a = "";

    // 툴바
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportlist);

        // 리스트뷰입니다.
        data = new ArrayList<ContactVO>();

        // 주소록에 표시될 20개의 가데이터를 삽입하자
        for(int i = 0; i<10;i++){
            data.add(new ContactVO("장소 "+i, "기간"+i, "시간" + i));
        }

        // 어댑터 생성(페이지정보, 항목 뷰 디자인, 아이템);
        adapter = new ContactAdapter(getApplicationContext(), R.layout.item_cardview2, data);

        lv_contact = findViewById(R.id.lv_contact);

        lv_contact.setAdapter(adapter);


        // 끝

        String a = SharedPreference.getAttribute(getBaseContext(), "id");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);  // 툴바 뒤로가기 누르면 여기로 간다!
                startActivity(intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }




}