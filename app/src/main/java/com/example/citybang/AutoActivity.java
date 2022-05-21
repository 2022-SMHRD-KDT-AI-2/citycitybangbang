package com.example.citybang;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.citybang.databinding.ActivityAutoBinding;

import java.util.Calendar;

public class AutoActivity extends AppCompatActivity {

    Button autoBtnCancel,autoBtnArea, autoBtnClock, autoBtnsiren;
    TextView autoTvArea, autoTvClock, autoEtContent;
    private int myYear, myMonth, myDay, myHour, myMinute;

    RequestQueue requestQueue;

    TextView autoTvLocation ;
    Button autoBtnLocation;
    int LOTATE = 1004;

    ActivityAutoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        autoTvArea = findViewById(R.id.autoTvArea);
        autoTvClock = findViewById(R.id.autoTvClock);
        autoTvLocation = findViewById(R.id.autoTvLocation);
        autoEtContent = findViewById(R.id.autoEtContent);

        String a = SharedPreference.getAttribute(getBaseContext(), "id");

        // 툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // 신고하기 클릭 시!!

        autoBtnsiren = findViewById(R.id.autoBtnsiren);
        autoBtnsiren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String acc_date = autoTvArea.getText().toString() + " " + autoTvClock.getText().toString();
                    String acc_place = autoTvLocation.getText().toString();
                    String re_comment = autoEtContent.getText().toString();

                    String url = "http://125.136.66.65:8090/citycitybangbang/report?id=" + a +
                            "&acc_date=" + acc_date + "&acc_place=" + acc_place + "&re_comment=" + re_comment;

                    StringRequest request = new StringRequest(
                            Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("신고 완료!")) {
                                Intent intent = new Intent(getApplicationContext(),SplashingActivity.class);
                                startActivity(intent);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AutoActivity.this, "응답 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                    );

                    requestQueue.add(request);

                    finish();
                }
        });

        // 위치 찾기
        autoTvLocation = findViewById(R.id.autoTvLocation);
        autoBtnLocation = findViewById(R.id.autoBtnLocation);

        final Intent[] intent = {new Intent(AutoActivity.this, MapActivity.class)};

        autoBtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent[0], LOTATE);
            }
        });
        String address = getIntent().getStringExtra("address");
        String address2 = getIntent().getStringExtra("address2");
        String address3 = address+ " " + address2;

        autoTvLocation.setText(address3);



        // 신고 취소하겟냐? 팝업창
        autoBtnCancel = findViewById(R.id.autoBtnCancel);
        autoBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(AutoActivity.this);
                ad.setTitle("신고를 취소하시겠습니까?");
                ad.setMessage("신고를 취소하시면 입력하신 내용은 저장되지 않습니다.");

                ad.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(AutoActivity.this,MainActivity.class);
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

        // 날짜 찾기
        final Calendar c = Calendar.getInstance();
        Button datePickerButton = (Button) findViewById(R.id.autoBtnArea);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myYear = c.get(Calendar.YEAR);
                myMonth = c.get(Calendar.MONTH);
                myDay = c.get(Calendar.DAY_OF_MONTH);

                Dialog dlgDate = new DatePickerDialog(AutoActivity.this, myDateSetListener,
                        myYear, myMonth, myDay);
                dlgDate.show();

            }
        });

        // 시간 찾기
        Button timePickerButton = (Button) findViewById(R.id.autoBtnClock);
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHour = c.get(Calendar.HOUR_OF_DAY);
                myMinute = c.get(Calendar.MINUTE);
                Dialog dlgTime = new TimePickerDialog(AutoActivity.this, myTimeSetListener,
                        myHour, myMinute, false);
                dlgTime.show();
            }
        });





    }

    private DatePickerDialog.OnDateSetListener myDateSetListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String date = String.valueOf(year) + "년 " +
                    String.valueOf(monthOfYear + 1) + "월 "
                    + String.valueOf(dayOfMonth) + "일";
            TextView autoTvArea;
            autoTvArea = findViewById(R.id.autoTvArea);
            autoTvArea.setText(date);
        }
    };

    private TimePickerDialog.OnTimeSetListener myTimeSetListener
            = new TimePickerDialog.OnTimeSetListener() {


        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time = String.valueOf(hourOfDay) + ":"
                    + String.valueOf(minute);
            TextView autoTvClock;
            autoTvClock = findViewById(R.id.autoTvClock);
            autoTvClock.setText(time);
        }
    };
    // 현재 위치 값!
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOTATE){
            if(resultCode == RESULT_OK){
                autoTvLocation.setText(data.getStringExtra("address"));
            }
        }



    }





}