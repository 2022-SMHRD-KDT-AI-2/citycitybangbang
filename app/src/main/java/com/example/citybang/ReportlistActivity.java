package com.example.citybang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ReportlistActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    String a = "";

    String[] b;

    String[][] c;

    // 툴바
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportlist);

        int cnt1=0, cnt2=0, cnt3=0;

        String a = SharedPreference.getAttribute(getBaseContext(), "id");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        Intent intent = getIntent();
        String re_list = intent.getStringExtra("report");

        Log.d("list", re_list);

        b = re_list.split(",");

        c = new String[b.length][];

        for (int i=0;i<b.length;i++){
            c[i] = b[i].split(";");
        }

        for (int i=0;i<b.length;i++) {
            if (c[i][2].equals("N")) {
                cnt2++;
            } else if (c[i][2].equals("Y")) {
                cnt3++;
            }
        }

        Log.d("cou", c[0][2]);

        cnt1 = b.length;

        String count = "신고접수 " + cnt1 + "건, " + "처리중 " + cnt2 + "건, " + "처리완료 " + cnt3  + "건";

        Log.d("count", count);
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