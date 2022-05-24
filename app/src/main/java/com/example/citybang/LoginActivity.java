package com.example.citybang;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.citybang.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    RequestQueue requestQueue;

    private String idd;
    private String pwd;

    private boolean saveLoginData;
    private SharedPreferences appData;
    private CheckBox checkBox;
    private EditText idText;
    private EditText pwdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkBox = binding.cbLogSave;
        idText = (EditText)findViewById(R.id.etLogId);
        pwdText = (EditText)findViewById(R.id.etLogPw);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        // 네트워크 통신

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        if (saveLoginData) {
            idText.setText(idd);
            pwdText.setText(pwd);
            checkBox.setChecked(saveLoginData);
        }

        binding.btnLogLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = binding.etLogId.getText().toString();
                String pw = binding.etLogPw.getText().toString();

                SharedPreference.setAttribute(getBaseContext(), "id", binding.etLogId.getText().toString());

                String url = "http://125.136.66.65:8081/citycitybangbang/login?id=" + id + "&pw=" + pw;

                StringRequest request = new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("로그인 성공!")){
                            save();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);


                        }else if(response.equals("로그인에 실패했습니다.")){
                            Toast.makeText(LoginActivity.this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "응답 실패", Toast.LENGTH_SHORT).show();
                    }
                }
                );

                requestQueue.add(request);
            }
        });

        binding.btnLogMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
                startActivity(intent);
            }
        });
    }

    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putBoolean("SAVE_LOGIN_DATA", checkBox.isChecked());
        editor.putString("ID", idText.getText().toString().trim());
        editor.putString("PWD", pwdText.getText().toString().trim());

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }

    // 설정값을 불러오는 함수
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        idd = appData.getString("ID", "");
        pwd = appData.getString("PWD", "");
    }



}