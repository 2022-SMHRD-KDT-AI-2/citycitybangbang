package com.example.citybang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
    String result;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 네트워크 통신

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        binding.btnLogLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.etLogId.getText().toString();
                String pw = binding.etLogPw.getText().toString();

                String url = "http://220.80.33.17:8090/citycitybangbang/login?id=" + id + "&pw=" + pw;

                StringRequest request = new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("로그인 성공!")){
                            Intent intent = new Intent(LoginActivity.this, splashActivity1.class);
                            startActivity(intent);
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
                Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
                startActivity(intent);
            }
        });






    }
}