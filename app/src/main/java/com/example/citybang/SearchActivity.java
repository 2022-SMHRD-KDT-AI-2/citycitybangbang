package com.example.citybang;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    private EditText et_address,et_address2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // 툴바 끝

        et_address2 = findViewById(R.id.et_address2);
        et_address = findViewById(R.id.et_address);

        et_address.setFocusable(false);
        et_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 주소 검색 웹뷰 화면으로 이동
                Intent intent = new Intent(SearchActivity.this,WebviewSearchActivity.class);
                getSearchResult.launch(intent);
            }
        });

        btn1 = findViewById(R.id.btn1);
        Intent intent = new Intent();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("address",et_address.getText().toString());
                intent.putExtra("address2",et_address2.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    private final ActivityResultLauncher<Intent> getSearchResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // SearchActivity로 부터의 결과값이 이곳으로 전달된다 (setResult에 의해)
                if (result.getResultCode() == RESULT_OK){
                    if (result.getData() != null){
                        String data = result.getData().getStringExtra("data");
                        et_address.setText(data);
                    }
                }
            }
    );


}