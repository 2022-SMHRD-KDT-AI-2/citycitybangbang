package com.example.citybang;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShootselectActivity extends AppCompatActivity {

    static File currentPhotoFile;
    static Uri currentPhotoUri;
    static String currentPhotoPath;
    static String currentPhotoFileName;


    private static final String TAG = "CameraActivity";

    public static final int REQUEST_TAKE_PHOTO = 10;
    public static final int REQUEST_PERMISSION = 11;

    private Button btnShoCamera,btnShoSiren;
    private ImageView ivShoReport;
    private String mCurrentPhotoPath;

    // 툴바
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shootselect);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        checkPermission(); //권한체크

        ivShoReport = findViewById(R.id.ivShoReport); //ImageView 선언
        btnShoCamera = findViewById(R.id.btnShoCamera); // 카메라
        btnShoSiren = findViewById(R.id.btnShoSiren); // 신고하기

        loadImgArr();

        //촬영
        btnShoCamera.setOnClickListener(v -> captureCamera());



        btnShoSiren.setOnClickListener(v ->{

            try{
                File file = new File(mCurrentPhotoPath);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));


                ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);







                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                float scale = (float) (1024/(float)bitmap.getWidth());
                int image_w = (int) (bitmap.getWidth()*scale);
                int image_h = (int) (bitmap.getHeight()*scale);
                Bitmap resize = Bitmap.createScaledBitmap(bitmap,image_w,image_h,true);

                resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                //ivShoReport.setImageBitmap(bitmap);

                Intent intent = new Intent(getApplicationContext(), SelfActivity.class);
                intent.putExtra("image",byteArray);

                startActivity(intent);
                finish();

            }
            catch (Exception e) {
                Toast.makeText(this, "load failed", Toast.LENGTH_SHORT).show();
            }


        });


    }
        private void captureCamera() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // 인텐트를 처리 할 카메라 액티비티가 있는지 확인
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                // 촬영한 사진을 저장할 파일 생성
                File photoFile = null;

                try {
                    //임시로 사용할 파일이므로 경로는 캐시폴더로
                    File tempDir = getCacheDir();

                    //임시촬영파일 세팅
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName = "Capture_" + timeStamp + "_"; //ex) Capture_20201206_

                    File tempImage = File.createTempFile(
                            imageFileName,  /* 파일이름 */
                            ".jpg",         /* 파일형식 */
                            tempDir      /* 경로 */
                    );

                    // ACTION_VIEW 인텐트를 사용할 경로 (임시파일의 경로)
                    mCurrentPhotoPath = tempImage.getAbsolutePath();

                    photoFile = tempImage;

                } catch (IOException e) {
                    //에러 로그는 이렇게 관리하는 편이 좋다.
                    Log.w(TAG, "파일 생성 에러!", e);
                }

                //파일이 정상적으로 생성되었다면 계속 진행
                if (photoFile != null) {
                    //Uri 가져오기
                    Uri photoURI = FileProvider.getUriForFile(this,
                            getPackageName() + ".fileprovider",
                            photoFile);
                    //인텐트에 Uri담기
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                    //인텐트 실행
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }



        private void loadImgArr() {
            try {


                File path = Environment.getExternalStoragePublicDirectory("/gogo");
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String filename = "JPEG_" + timeStamp + "_";


                File file = new File(path, filename);
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                ivShoReport.setImageBitmap(bitmap);
                //신고 페이지

            } catch (Exception e) {
                Toast.makeText(this, "사진촬영하기", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            super.onActivityResult(requestCode, resultCode, intent);
            try {
                //after capture
                switch (requestCode) {
                    case REQUEST_TAKE_PHOTO: {
                        if (resultCode == RESULT_OK) {

                            File file = new File(mCurrentPhotoPath);
                            Bitmap bitmap = MediaStore.Images.Media
                                    .getBitmap(getContentResolver(), Uri.fromFile(file));

                            if (bitmap != null) {
                                ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                        ExifInterface.ORIENTATION_UNDEFINED);


                                Bitmap rotatedBitmap = null;
                                switch (orientation) {

                                    case ExifInterface.ORIENTATION_ROTATE_90:
                                        rotatedBitmap = rotateImage(bitmap, 90);
                                        break;

                                    case ExifInterface.ORIENTATION_ROTATE_180:
                                        rotatedBitmap = rotateImage(bitmap, 180);
                                        break;

                                    case ExifInterface.ORIENTATION_ROTATE_270:
                                        rotatedBitmap = rotateImage(bitmap, 270);
                                        break;

                                    case ExifInterface.ORIENTATION_NORMAL:
                                    default:
                                        rotatedBitmap = bitmap;
                                }

                                //Rotate한 bitmap을 ImageView에 저장
                                ivShoReport.setImageBitmap(rotatedBitmap);

                            }
                        }
                        break;
                    }
                }

            } catch (Exception e) {

            }
        }

        //카메라에 맞게 이미지 로테이션
        public static Bitmap rotateImage(Bitmap source, float angle) {
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                    matrix, true);
        }



    }

