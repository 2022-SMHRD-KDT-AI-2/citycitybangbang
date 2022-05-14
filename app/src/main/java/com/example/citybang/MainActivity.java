package com.example.citybang;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    Button btnMaiCamera,btnMaiSiren,btnMaiGallery;

    Button btnDraWithdrawal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMaiCamera = findViewById(R.id.btnMaiCamera);
        btnMaiSiren = findViewById(R.id.btnMaiSiren);
        btnMaiGallery = findViewById(R.id.btnMaiGallery);

        Button button = findViewById(R.id.btnDraWithdrawal);
        button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btnDraWithdrawal = findViewById(R.id.btnDraWithdrawal);

        btnDraWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setTitle("회원탈퇴");
                ad.setMessage("회원탈퇴를 하시면 해당 아이디로 로그인이되지 않습니다. 회원탈퇴 하시겠습니까?");

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ad.show();
            }
        });


    }
}