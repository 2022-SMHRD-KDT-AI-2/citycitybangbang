package com.example.citybang;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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

import java.util.Calendar;

public class AutoActivity extends AppCompatActivity {

    Button autoBtnCancel,autoBtnArea, autoBtnClock;
    TextView autoTvArea, autoTvClock;
    private int myYear, myMonth, myDay, myHour, myMinute;

    TextView autoTvLocation;
    Button autoBtnLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        // 툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // 위치 찾기
        autoTvLocation = findViewById(R.id.autoTvLocation);
        autoBtnLocation = findViewById(R.id.autoBtnLocation);

        Intent intent = new Intent(AutoActivity.this,SearchActivity.class);

        autoBtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
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



}