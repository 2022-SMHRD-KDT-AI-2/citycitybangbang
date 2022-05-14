package com.example.citybang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QnaActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListviewAdapter listAdapter;
    List<String> chapterList;
    HashMap<String, List<String>> topicList;

    // 툴바
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);

        expandableListView = findViewById(R.id.eListview);

        showList();

        listAdapter = new ExpandableListviewAdapter(this, chapterList, topicList);
        expandableListView.setAdapter(listAdapter);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);  // 툴바 뒤로가기 누르면 여기로 간다!
                startActivity(intent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showList(){
        chapterList = new ArrayList<String>();
        topicList = new HashMap<String, List<String>>();

        chapterList.add("신고는 어떻게 하나요");
        chapterList.add("회원가입");
        chapterList.add("회원 탈퇴");
        chapterList.add("로그인 안하고 신고하는법");
        chapterList.add("내가 신고한 건 확인하기");

        List<String> topic1 = new ArrayList<>();
        topic1.add("완성후 수정예정");
        List<String> topic2 = new ArrayList<>();
        topic2.add("완성후 수정예정");
        List<String> topic3 = new ArrayList<>();
        topic3.add("완성후 수정예정");
        List<String> topic4 = new ArrayList<>();
        topic4.add("완성후 수정예정");
        List<String> topic5 = new ArrayList<>();
        topic5.add("완성후 수정예정");

        topicList.put(chapterList.get(0),topic1);
        topicList.put(chapterList.get(1),topic2);
        topicList.put(chapterList.get(2),topic3);
        topicList.put(chapterList.get(3),topic4);
        topicList.put(chapterList.get(4),topic5);



    }





}