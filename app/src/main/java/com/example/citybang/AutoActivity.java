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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.InputStream;
import java.util.Calendar;

public class AutoActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 20;

    Button autoBtnCancel,autoBtnArea, autoBtnClock, autoBtnsiren, autoBtnGallery;
    TextView autoTvArea, autoTvClock, autoEtContent;
    private int myYear, myMonth, myDay, myHour, myMinute;

    ImageView autoImg;

    RequestQueue requestQueue;

    TextView autoTvLocation ;
    Button autoBtnLocation;
    int LOTATE = 1004;

    String address="";

    Uri uri;
    ActivityAutoBinding binding;

    // 현재 위치 값!
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOTATE){

            if(resultCode == RESULT_OK){
                address = data.getStringExtra("address");
                autoTvLocation.setText(address);
            }else if(resultCode == RESULT_CANCELED){
                address = data.getStringExtra("test");
                String address2 = data.getStringExtra("test2");
//                String address3 = address+ " " + address2;

                autoTvLocation.setText(address+ " " + address2);

            }
        }

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    autoImg.setImageBitmap(img);

                    uri = data.getData();

                    Toast.makeText(this, "uri확인" + uri, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }


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
                String acc_place = address;
                String re_comment = autoEtContent.getText().toString();

                if (acc_date != null){
                    if (acc_place != null){
                        String url = "http://125.136.66.65:8081/citycitybangbang/report?id=" + a +
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
                    }
                }
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

        // 갤러리버튼으로 사진 변경
        autoImg = findViewById(R.id.autoImg);
        autoBtnGallery = findViewById(R.id.autoBtnGallery);

        autoBtnGallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }

        });


    }

    private DatePickerDialog.OnDateSetListener myDateSetListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String date = String.valueOf(year) + "-" +
                    String.valueOf(monthOfYear + 1) + "-"
                    + String.valueOf(dayOfMonth);
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