package com.example.citybang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.widget.ImageView;

public class SelfActivity extends AppCompatActivity {

    Button selfBtnCancel, selfBtnsiren;
    TextView selfTvArea, selfTvClock, selfTvLocation;

    ImageView selfImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);

        selfTvArea = findViewById(R.id.selfTvArea);
        selfTvClock = findViewById(R.id.selfTvClock);
        selfTvLocation = findViewById(R.id.selfTvLocation);

        Date dt = new Date();

        SimpleDateFormat full_sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat full_sdf2 = new SimpleDateFormat("a hh:mm:ss");

        selfTvArea.setText(full_sdf1.format(dt).toString());
        selfTvClock.setText(full_sdf2.format(dt).toString());

        // 툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // 신고하기 클릭 시!!
        selfBtnsiren = findViewById(R.id.selfBtnsiren);
        selfBtnsiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelfActivity.this,SplashingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 신고 취소하겟냐? 팝업창
        selfBtnCancel = findViewById(R.id.selfBtnCancel);
        selfBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(SelfActivity.this);
                ad.setTitle("신고를 취소하시겠습니까?");
                ad.setMessage("신고를 취소하시면 입력하신 내용은 저장되지 않습니다.");

                ad.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(SelfActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                ad.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ad.show();

            }
        });
        // 팝업 창 끝

        // 현재 시간, 날짜 불러오기
        selfTvArea = (TextView) findViewById(R.id.selfTvArea);
        selfTvClock = (TextView) findViewById(R.id.selfTvClock);

        selfTvArea.setText(getDate());
        selfTvClock.setText(getTime());



        // 사진 바로 불러와 지는 코드
        selfImg=findViewById(R.id.selfImg);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


        selfImg.setImageBitmap(bitmap);

    }
    private String getDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);
        return getTime;
    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String getTime = dateFormat.format(date);
        return getTime;
    }



}