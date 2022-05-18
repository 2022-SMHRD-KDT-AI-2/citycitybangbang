package com.example.citybang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.citybang.databinding.ActivityMemberBinding;

public class MemberActivity extends AppCompatActivity {

    ActivityMemberBinding binding;
    RequestQueue requestQueue;
    String result;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // 네트워크 통신

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton){
                    result = binding.radioButton.getText().toString();
                }else if(i == R.id.radioButton2){
                    result = binding.radioButton2.getText().toString();
                }
            }
        });

        binding.btnMemCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.etMemId.getText().toString();

                String url = "http://121.147.185.123:8090/citycitybangbang/idCheck?id=" + id;

                StringRequest request = new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.equals("이미 존재하는 아이디 입니다.")){
                            binding.tvId.setTextColor(Color.parseColor("#23A462"));
                            binding.tvId.setText(response);

                        }else if ( response.equals("생성 가능한 아이디 입니다.")){
                            binding.tvId.setTextColor(Color.parseColor("#F33434"));
                            binding.tvId.setText(response);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MemberActivity.this, "응답 실패", Toast.LENGTH_SHORT).show();
                    }
                }
                );

                requestQueue.add(request);
            }
        });

        binding.btnLogMem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.etMemId.getText().toString();
                String pw = binding.erMemPw.getText().toString();
                String email = binding.editTextTextPersonName.getText().toString();
                String name = binding.editTextTextPersonName2.getText().toString();
                sex = result;


                String url = "http://125.136.66.65:8090/citycitybangbang/androidDB?id=";
                url += id;
                url += "&pw=";
                url += pw;
                url += "&email=" + email + "&name=" + name + "&sex=" + sex;

                StringRequest request = new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("회원 가입 성공!")){
                            Intent intent = new Intent(MemberActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MemberActivity.this, "응답 실패", Toast.LENGTH_SHORT).show();
                    }
                }
                );

                requestQueue.add(request);
            }
        });




        binding.btnDraSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });






    }
}