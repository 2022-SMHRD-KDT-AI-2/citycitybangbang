package com.example.citybang;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    Button btnMaiCamera, btnMaiSiren, btnMaiGallery, btnDraReportlist,
            btnDraLaw1, btnDraLaw2, btnDraAsk, btnDraStep, btnDraWithdrawal,
            btnLogin, btnMember, btnLogout;

    Intent intent;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String a = SharedPreference.getAttribute(getBaseContext(), "id");

        btnMaiCamera = findViewById(R.id.btnMaiCamera);
        btnMaiSiren = findViewById(R.id.btnMaiSiren);
        btnMaiGallery = findViewById(R.id.btnMaiGallery);
        btnDraReportlist = findViewById(R.id.btnDraReportlist);
        btnDraLaw1 = findViewById(R.id.btnDraLaw1);
        btnDraLaw2 = findViewById(R.id.btnDraLaw2);
        btnDraAsk = findViewById(R.id.btnDraAsk);
        btnDraStep = findViewById(R.id.btnDraStep);
        btnDraWithdrawal = findViewById(R.id.btnDraWithdrawal);
        btnLogin = findViewById(R.id.btnLogin);
        btnMember = findViewById(R.id.btnMember);
        btnLogout = findViewById(R.id.btnLogout);

        // 회원탈퇴 밑줄
        Button button = findViewById(R.id.btnDraWithdrawal);
        button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        // 밑줄 끝

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        // 팝업창
        btnDraWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setTitle("회원탈퇴");
                ad.setMessage("회원탈퇴를 하시면 해당 아이디로 로그인이되지 않습니다. 회원탈퇴 하시겠습니까?");

                ad.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String url = "http://125.136.66.65:8090/citycitybangbang/deleteUser?id=" + a;
                        StringRequest request = new StringRequest(
                                Request.Method.GET, url, new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response)
                            {
                                if(response.equals("회원 탈퇴 성공!")){
                                    Toast.makeText(MainActivity.this, "회원 탈퇴 성공!", Toast.LENGTH_SHORT).show();
                                }else if ( response.equals("0")){

                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "응답 실패", Toast.LENGTH_SHORT).show();
                            }
                        }
                        );

                        requestQueue.add(request);
                        dialogInterface.dismiss();
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
        // 팝업창 끝

        if (a==null) {
            btnMember.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);

            // 로그인 클릭 시
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            //회원가입 클릭 시
            btnMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(MainActivity.this, MemberActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (a!= null){
            btnLogin.setVisibility(View.GONE);
            btnMember.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreference.removeAttribute(getBaseContext(), "id");
                    intent = new Intent(MainActivity.this, splashActivity1.class);
                    startActivity(intent);
                }
            });


        }


        // 갤러리로 이동
        btnMaiGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,GalaryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 신고하기로 이동
        btnMaiSiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,AutoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 나의 신고내역 이동
        btnDraReportlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,ReportlistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 도로 교통법 이동
        btnDraLaw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,LawActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 법령 이동
        btnDraLaw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,LawActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        // 자주하는 질문 이동
        btnDraAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,QnaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 절차 이동
        btnDraStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,ImformationActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}