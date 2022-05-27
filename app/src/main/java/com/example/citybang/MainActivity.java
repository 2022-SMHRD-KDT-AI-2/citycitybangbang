package com.example.citybang;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_PERMISSION = 11;

    Button btnMaiCamera, btnMaiSiren, btnMaiList, btnDraReportlist,
            btnDraLaw1, btnDraLaw2, btnDraAsk, btnDraStep, btnDraWithdrawal,
            btnLogin, btnMember, btnLogout;

    TextView tvMaiToday, tvMaiMonth;

    Intent intent;

    RequestQueue requestQueue;

    String list;

    String rN[];

    String rNd[];

    String Month[];

    String Today;

    int cnt1=0,cnt2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String a = SharedPreference.getAttribute(getBaseContext(), "id");

        Log.d("final", getDate());

        btnMaiCamera = findViewById(R.id.btnMaiCamera);
        btnMaiSiren = findViewById(R.id.btnMaiSiren);
        btnMaiList = findViewById(R.id.btnMaiList);
        btnDraReportlist = findViewById(R.id.btnDraReportlist);
        btnDraLaw1 = findViewById(R.id.btnDraLaw1);
        btnDraLaw2 = findViewById(R.id.btnDraLaw2);
        btnDraAsk = findViewById(R.id.btnDraAsk);
        btnDraStep = findViewById(R.id.btnDraStep);
        btnDraWithdrawal = findViewById(R.id.btnDraWithdrawal);
        btnLogin = findViewById(R.id.btnLogin);
        btnMember = findViewById(R.id.btnMember);
        btnLogout = findViewById(R.id.btnLogout);
        tvMaiToday = findViewById(R.id.tvMaiToday);
        tvMaiMonth = findViewById(R.id.tvMaiMonth);

        Button button = findViewById(R.id.btnDraWithdrawal);

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }


        String url = "http://125.136.66.65:8081/citycitybangbang/reportNum?";
        StringRequest request = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response)
            {
                if(response.length()>2) {
                    list = response.substring(1, response.length() - 1);

                    rN = list.split(", ");

                    Log.d("finl", list);


                    rNd = new String[rN.length];

                    Month = new String[rNd.length];


                    for (int i = 0; i < rN.length; i++) {
                        rNd[i] = rN[i].substring(0, 10);
                    }

                    for (int i = 0; i < rNd.length; i++) {
                        if (rNd[i].equals(getDate())) {
                            cnt1++;
                        }
                    }

                    for (int i = 0; i < rNd.length; i++) {
                        Month[i] = rNd[i].substring(5, 7);
                    }

                    tvMaiToday.setText(String.valueOf(cnt1));

                    Today = getDate().substring(5, 7);

                    for (int i = 0; i < Month.length; i++) {
                        if (Month[i].equals(Today)) {
                            cnt2++;
                        }
                    }

                    tvMaiMonth.setText(String.valueOf(cnt2));
                }else{
                    tvMaiToday.setText(String.valueOf(0));
                    tvMaiMonth.setText(String.valueOf(0));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "응답 실패", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);

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

                        String url = "http://125.136.66.65:8081/citycitybangbang/deleteUser?id=" + a;
                        StringRequest request = new StringRequest(
                                Request.Method.GET, url, new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response)
                            {
                                if(response.equals("회원 탈퇴 성공!")){
                                    SharedPreference.removeAttribute(getBaseContext(), "id");
                                    Toast.makeText(getApplicationContext(), "회원 탈퇴 성공!", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(getApplicationContext(), splashActivity1.class);
                                    startActivity(intent);
                                }else if ( response.equals("0")){

                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "응답 실패", Toast.LENGTH_SHORT).show();
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
            button.setVisibility(View.GONE);

            // 로그인 클릭 시
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            //회원가입 클릭 시
            btnMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(getApplicationContext(), MemberActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (a!= null){
            btnLogin.setVisibility(View.GONE);
            btnMember.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreference.removeAttribute(getBaseContext(), "id");
                    intent = new Intent(getApplicationContext(), splashActivity1.class);
                    startActivity(intent);
                }
            });


        }


        // 갤러리로 이동
        btnMaiList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://125.136.66.65:8081/citycitybangbang/reSuccess?id=" + a;
                StringRequest request = new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        if (a != null){
                            if(response.length()>2) {
                                Log.d("check", response.substring(1,response.length()-2));
                                Intent intent1 = new Intent(getApplicationContext(), ReportlistActivity.class);
                                intent1.putExtra("report", response.substring(1,response.length()-2));
                                startActivity(intent1);
                            }else if(response.length() == 2){
                                Toast.makeText(MainActivity.this, "신고 없음", Toast.LENGTH_SHORT).show();
                            }
                        }else if(a == null) {
                            Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent1);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "응답 실패", Toast.LENGTH_SHORT).show();
                    }
                }
                );

                requestQueue.add(request);
            }
        });

        // 신고하기로 이동
        btnMaiSiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(a == null) {
                    Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent1);
                }else if(a != null){
                    intent = new Intent(getApplicationContext(),AutoActivity.class);
                    startActivity(intent);
                }

            }
        });

        // 나의 신고내역 이동
        btnDraReportlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://125.136.66.65:8081/citycitybangbang/reSuccess?id=" + a;
                StringRequest request = new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        if (a != null){
                            if(response.length()>2) {
                                Log.d("check", response.substring(1,response.length()-2));
                                Intent intent1 = new Intent(getApplicationContext(), ReportlistActivity.class);
                                intent1.putExtra("report", response.substring(1,response.length()-2));
                                startActivity(intent1);
                            }else if(response.length() == 2){
                                Toast.makeText(MainActivity.this, "신고 없음", Toast.LENGTH_SHORT).show();
                            }
                        }else if(a == null) {
                            Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent1);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "응답 실패", Toast.LENGTH_SHORT).show();
                    }
                }
                );

                requestQueue.add(request);
            }
        });

        // 도로 교통법 이동
        btnDraLaw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),LawActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 법령 이동
        btnDraLaw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),LawActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        // 자주하는 질문 이동
        btnDraAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),QnaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 절차 이동
        btnDraStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),ImformationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 카메라 이동
        btnMaiCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkPermission(); //권한체크

                if(a == null) {
                    Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent1);
                }else if(a != null) {
                    intent = new Intent(getApplicationContext(), ShootselectActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private String getDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);
        return getTime;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkPermission(); //권한체크
    }

    //권한 확인
    public void checkPermission() {
        int permissionCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permissionRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //권한이 없으면 권한 요청
        if (permissionCamera != PackageManager.PERMISSION_GRANTED
                || permissionRead != PackageManager.PERMISSION_GRANTED
                || permissionWrite != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "이 앱을 실행하기 위해 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                // 권한이 취소되면 result 배열은 비어있다.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "권한 확인", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();
                    finish(); //권한이 없으면 앱 종료
                }
            }
        }
    }


}