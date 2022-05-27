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
        setContentView(R.layout.activity_qna);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        expandableListView = findViewById(R.id.eListview);
        showList();
        listAdapter = new ExpandableListviewAdapter(this, chapterList, topicList);
        expandableListView.setAdapter(listAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);  // 툴바 뒤로가기 누르면 여기로 간다!
                startActivity(intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showList(){
        chapterList = new ArrayList<String>();
        topicList = new HashMap<String, List<String>>();

        chapterList.add("자동 신고는 어떻게 하나요");
        chapterList.add("수동 신고는 어떻게 하나요");
        chapterList.add("회원가입은 꼭 해야하나요");
        chapterList.add("회원 탈퇴는 어떻게 하나요");
        chapterList.add("로그인 안하고 신고하기도 가능한가요");
        chapterList.add("신고한 걸 어떻게 확인하나요");

        List<String> topic1 = new ArrayList<>();
        topic1.add("메인 페이지에서 촬영하기를 누른 후 촬영된 사진을 확인하여 신고 버튼을 누르시면 GPS 기반 해당 사진 위치가 " +
                "저장되어 자동으로 전달 됩니다");
        List<String> topic2 = new ArrayList<>();
        topic2.add("메인 페이지에서 신고하기를 누른 후 촬영된 사진을 첨부하고 목격한 일자, 시간, 위치를 입력 후 신고 버튼을 " +
                " 누르시면 저장되어 전달 됩니다");
        List<String> topic3 = new ArrayList<>();
        topic3.add("일부 악성 신고를 제어하기 위한 안전 장치이며 신고한 민원의 처리 여부도 받아 볼 수가 있기때문에" +
                "회원 가입을 하시는걸 추천 드립니다.");
        List<String> topic4 = new ArrayList<>();
        topic4.add("회원가입후 우측 상단에 있는 사이드 메뉴바 버튼을 클릭후 해당 메뉴 하단에 있는 회원 탈퇴를 클릭하시면 안전하게 회원 탈퇴가 가능합니다");
        List<String> topic5 = new ArrayList<>();
        topic5.add("가능합니다");
        List<String> topic6 = new ArrayList<>();
        topic6.add("해당기능은 회원가입자에 한에 제공하는 기능이기 때문에 민원의 처리 여부를 확인 하시려면 회원가입을 해주시길 바랍니다.");

        topicList.put(chapterList.get(0),topic1);
        topicList.put(chapterList.get(1),topic2);
        topicList.put(chapterList.get(2),topic3);
        topicList.put(chapterList.get(3),topic4);
        topicList.put(chapterList.get(4),topic5);
        topicList.put(chapterList.get(5),topic6);



    }





}