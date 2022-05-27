package com.example.citybang;

import android.Manifest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AutoActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 20;
    private static final int REQUEST_PERMISSION = 11;

    Button autoBtnCancel,autoBtnArea, autoBtnClock, autoBtnsiren, autoBtnGallery;
    TextView autoTvArea, autoTvClock, autoEtContent;
    private int myYear, myMonth, myDay, myHour, myMinute;

    ImageView autoImg;

    RequestQueue requestQueue;

    TextView autoTvLocation ;
    Button autoBtnLocation;
    int LOTATE = 1004;

    String a = "";

    String address="";

    private String img_path = new String();
    private Bitmap image_bitmap_copy = null;
    private Bitmap image_bitmap = null;
    private String imageName = null;

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
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) { }
        }

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    img_path = getImagePathToUri(data.getData()); //이미지의 URI를 얻어 경로값으로 반환.
                    //이미지를 비트맵형식으로 반환
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    //사용자 단말기의 width , height 값 반환
                    int reWidth = (int) (getWindowManager().getDefaultDisplay().getWidth());
                    int reHeight = (int) (getWindowManager().getDefaultDisplay().getHeight());

                    //image_bitmap 으로 받아온 이미지의 사이즈를 임의적으로 조절함. width: 400 , height: 300
                    image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, 300, true);
                    ImageView image = (ImageView) findViewById(R.id.imageView);  //이미지를 띄울 위젯 ID값
                    image.setImageBitmap(image_bitmap_copy);

                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        a = SharedPreference.getAttribute(getBaseContext(), "id");

        autoTvArea = findViewById(R.id.autoTvArea);
        autoTvClock = findViewById(R.id.autoTvClock);
        autoTvLocation = findViewById(R.id.autoTvLocation);
        autoEtContent = findViewById(R.id.autoEtContent);

        String a = SharedPreference.getAttribute(getBaseContext(), "id");

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

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

                autoBtnsiren.setClickable(false);
                autoBtnsiren.setText("신고 중");

                if (acc_date != null){
                    if (acc_place != null){
                        String url = "http://125.136.66.65:8081/citycitybangbang/report2?id=" + a +
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

                            }
                        }
                        );

                        //DoFileUpload(serverURL, img_path);
                        DoFileUpload(url, img_path);
                        Log.d("Send", "Success");

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
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }

        });




    }

    public String getImagePathToUri(Uri data) {
        //사용자가 선택한 이미지의 정보를 받아옴
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //이미지의 경로 값
        String imgPath = cursor.getString(column_index);
        Log.d("test", imgPath);

        //이미지의 이름 값
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
        this.imageName = imgName;

        return imgPath;
    }//end of getImagePathToUri()

    public void DoFileUpload(String apiUrl, String absolutePath) {
        HttpFileUpload(apiUrl, "", absolutePath);
    }

    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";

    public void HttpFileUpload(String urlString, String params, String fileName) {
        try {

            FileInputStream mFileInputStream = new FileInputStream(fileName);
            URL connectUrl = new URL(urlString);
            Log.d("Test", "mFileInputStream  is " + mFileInputStream);

            // HttpURLConnection 통신
            HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            // write data
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);

            int bytesAvailable = mFileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);

            byte[] buffer = new byte[bufferSize];
            int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);

            Log.d("Test", "image byte is " + bytesRead);

            // read image
            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = mFileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // close streams
            Log.e("Test", "File is written");
            mFileInputStream.close();
            dos.flush();
            // finish upload...

            // get response
            InputStream is = conn.getInputStream();

            StringBuffer b = new StringBuffer();
            for (int ch = 0; (ch = is.read()) != -1; ) {
                b.append((char) ch);
            }
            is.close();
            Log.e("Test", b.toString());


        } catch (Exception e) {
            Log.d("Test", "exception " + e.getMessage());
            // TODO: handle exception
        }
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