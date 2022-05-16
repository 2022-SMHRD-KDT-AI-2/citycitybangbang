package com.example.citybang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GalaryActivity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 그리드 어댑터 세팅: 이미지 처리를 위한 이미지 어댑터를 그리드뷰에 전달
        gridView = (GridView) findViewById(R.id.gridView01);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        // 이벤트 처리를 위한 부분
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), (++position) + "번째 이미지 선택", Toast.LENGTH_SHORT).show();
            }
        });
    }
}