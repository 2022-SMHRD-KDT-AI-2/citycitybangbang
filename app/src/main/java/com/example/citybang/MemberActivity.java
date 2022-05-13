package com.example.citybang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

                String url = "http://220.80.33.17:8090/web/idCheck.jsp?id=" + id;

                StringRequest request = new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        binding.tvId.setText(response);
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


                String url = "http://220.80.33.17:8090/web/androidDB.jsp?id=";
                url += id;
                url += "&pw=";
                url += pw;
                url += "&email=" + email + "&name=" + name + "&sex=" + sex;

                StringRequest request = new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MemberActivity.this, "응답 성공", Toast.LENGTH_SHORT).show();
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
    }
}