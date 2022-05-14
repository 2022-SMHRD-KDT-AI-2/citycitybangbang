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

public class lawActivity extends AppCompatActivity {

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
    private void showList(){
        chapterList = new ArrayList<String>();
        topicList = new HashMap<String, List<String>>();

        chapterList.add("이륜 자동차의 기본 정의");
        chapterList.add("고속도로 통행 불가");
        chapterList.add("주행중 안전장비 착용");
        chapterList.add("번호판 훼손 벌금");
        chapterList.add("번호판 미부착 벌금");

        List<String> topic1 = new ArrayList<>();
        topic1.add("도로 교통법 제2조(정의)\n\n" +
                "“자동차”란 철길이나 가설된 선을 이용하지 아니하고 원동기를 사용하여 운전되는 차(견인되는 자동차도 자동차의 일부로 본다)로서 다음 각 목의 차를 말한다.\n" +
                "가. 「자동차관리법」 제3조에 따른 다음의 자동차. 다만, 원동기장치자전거는 제외한다.\n" +
                "1) 승용자동차\n" +
                "2) 승합자동차\n" +
                "3) 화물자동차\n" +
                "4) 특수자동차\n" +
                "5) 이륜자동차");
        List<String> topic2 = new ArrayList<>();
        topic2.add("도로 교통법 제63조(통행 등의 금지) \n\n" +
                "자동차(이륜자동차는 긴급자동차만 해당한다) 외의 차마의 운전자 또는 보행자는 고속도로등을 통행하거나 횡단하여서는 아니 된다.");
        List<String> topic3 = new ArrayList<>();
        topic3.add("도로 교통법 제50조(특정 운전자의 준수사항)\n\n" +
                "이륜자동차와 원동기장치자전거(개인형 이동장치는 제외한다)의 운전자는 행정안전부령으로 정하는 인명보호 장구를 착용하고 운행하여야 하며, 동승자에게도 착용하도록 하여야 한다.");
        List<String> topic4 = new ArrayList<>();
        topic4.add("자동차 관리법 제81조(벌칙)\n\n 다음 각 호의 어느 하나에 해당하는 자는 1년 이하의 징역 또는 1천만원 이하의 벌금에 처한다.\n" +
                "제52조에서 준용하는 경우를 포함한다)을 위반하여 고의로 등록번호판을 가리거나 알아보기 곤란하게 한 자" +
                "5) 이륜자동차");
        List<String> topic5 = new ArrayList<>();
        topic5.add("자동차 관리법 제84조(과태료)\n\n" + "① 다음 각 호의 어느 하나에 해당하는 자에게는 2천만원 이하의 과태료를 부과한다.\n" +
                "제49조제1항을 위반하여 이륜자동차번호판을 붙이지 아니하고 이륜자동차를 운행한 자");

        topicList.put(chapterList.get(0),topic1);
        topicList.put(chapterList.get(1),topic2);
        topicList.put(chapterList.get(2),topic3);
        topicList.put(chapterList.get(3),topic4);
        topicList.put(chapterList.get(4),topic5);



    }





}