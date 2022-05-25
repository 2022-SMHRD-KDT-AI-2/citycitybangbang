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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReportlistActivity extends AppCompatActivity {

    ListView lv_contact;
    ContactAdapter adapter;
    List<ContactVO> data;

    TextView repTv1, repTv2, repTv3;

    String a = "";

    String b[];

    String c[][];

    String d[];

    String e[];

    int cnt1=0, cnt2=0, cnt3=0;

    // 툴바
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportlist);

        repTv1 = findViewById(R.id.repTv1);
        repTv2 = findViewById(R.id.repTv2);
        repTv3 = findViewById(R.id.repTv3);

        String a = SharedPreference.getAttribute(getBaseContext(), "id");

        Intent intent1 = getIntent();
        String re_list = intent1.getStringExtra("report");

        Log.d("cou",re_list);

        b = re_list.split(",,");

        c = new String[b.length][];

        d = new String[b.length];

        e = new String[b.length];

        for (int i=0;i<b.length;i++){
            c[i] = b[i].split(";");
        }

        for (int i=0;i<b.length;i++) {
            if (c[i][2].charAt(0)=='N') {
                cnt2++;
            } else if (c[i][2].charAt(0)=='Y') {
                cnt3++;
            }
        }

        for (int i=0;i<b.length;i++){
            d[i] = c[i][0].substring(0,c[i][0].length()-5);
            e[i] = c[i][0].substring(c[i][0].length()-5);
        }

        cnt1 = b.length;

        String count = "신고접수 " + cnt1 + "건, " + "처리중 " + cnt2 + "건, " + "처리완료 " + cnt3  + "건";

        Log.d("count", count);

        repTv1.setText(String.valueOf(cnt1));
        repTv2.setText(String.valueOf(cnt2));
        repTv3.setText(String.valueOf(cnt3));


        // 리스트뷰
        data = new ArrayList<ContactVO>();

        // 주소록에 표시될 20개의 가데이터를 삽입하자
        for(int i = 0; i<b.length;i++){
            data.add(new ContactVO(d[i], e[i], c[i][1]));
        }

        // 어댑터 생성(페이지정보, 항목 뷰 디자인, 아이템);
        adapter = new ContactAdapter(getApplicationContext(), R.layout.item_cardview2, data);

        lv_contact = findViewById(R.id.lv_contact);

        lv_contact.setAdapter(adapter);


        // 끝

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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