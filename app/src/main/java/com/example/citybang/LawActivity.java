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

public class LawActivity extends AppCompatActivity {

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

        chapterList.add("이륜 자동차의 기본 정의");
        chapterList.add("고속도로 통행 불가");
        chapterList.add("주행중 안전장비 착용");
        chapterList.add("이륜자동차의 사용 신고");
        chapterList.add("이륜자동차번호판의 부착의무");

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
        topic4.add("도로 교통법 제48조(이륜자동차의 사용 신고 등) ① 국토교통부령으로 정하는 이륜자동차(이하 “이륜자동차”라 한다)를 취득하여 사용하려는 자는 국토교통부령으로 정하는 바에 따라 시장ㆍ군수ㆍ구청장에게 사용 신고를 하고 이륜자동차 번호의 지정을 받아야 한다. <개정 2011. 5. 24., 2013. 3. 23.>\n" +
                "\n" +
                "② 이륜자동차의 소유자는 제1항에 따른 신고 사항 중 국토교통부령으로 정하는 변경 사항이 있거나 이륜자동차 사용을 폐지한 경우에는 시장ㆍ군수ㆍ구청장에게 신고하여야 한다. <개정 2013. 3. 23.>\n" +
                "\n" +
                "③ 제1항에 따라 신고된 이륜자동차의 소유권을 이전받은 자는 국토교통부령으로 정하는 바에 따라 시장ㆍ군수ㆍ구청장에게 이륜자동차 소유권 이전에 관한 신고를 하여야 한다. 다만, 이륜자동차를 양수한 소유자가 이륜자동차 소유권 이전에 관한 신고를 하지 아니한 경우에는 이를 양도한 자가 국토교통부령으로 정하는 바에 따라 그 소유자를 갈음하여 신고할 수 있다. <개정 2013. 3. 23.>");
        List<String> topic5 = new ArrayList<>();
        topic5.add("도로 교통법 제49조(이륜자동차번호판의 부착의무)\n\n ① 이륜자동차는 그 후면의 보기 쉬운 곳에 국토교통부령으로 정하는 이륜자동차번호판을 붙이지 아니하고는 운행하지 못한다. <개정 2013. 3. 23., 2020. 6. 9.>\n" +
                "\n" +
                "② 시장ㆍ군수ㆍ구청장은 제48조제1항에 따른 사용 신고를 받으면 국토교통부령으로 정하는 바에 따라 해당 이륜자동차에 이륜자동차번호판을 붙이고 봉인을 하여야 한다. 다만, 이륜자동차의 사용 신고를 하는 자가 직접 이륜자동차번호판의 부착 및 봉인을 하려는 경우에는 국토교통부령으로 정하는 바에 따라 직접 하게 할 수 있다.");

        topicList.put(chapterList.get(0),topic1);
        topicList.put(chapterList.get(1),topic2);
        topicList.put(chapterList.get(2),topic3);
        topicList.put(chapterList.get(3),topic4);
        topicList.put(chapterList.get(4),topic5);




    }





}