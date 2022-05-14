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

public class LawActivity2 extends AppCompatActivity {

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


        chapterList.add("번호판 훼손");
        chapterList.add("번호판 미부착");
        chapterList.add("비인가 번호판 사용 또는 매매시");

        List<String> topic4 = new ArrayList<>();
        topic4.add("자동차 관리법 제81조(벌칙)\n\n 다음 각 호의 어느 하나에 해당하는 자는 1년 이하의 징역 또는 1천만원 이하의 벌금에 처한다.\n" +
                "제52조에서 준용하는 경우를 포함한다)을 위반하여 고의로 등록번호판을 가리거나 알아보기 곤란하게 한 자" +
                "5) 이륜자동차");
        List<String> topic5 = new ArrayList<>();
        topic5.add("자동차 관리법 제84조(과태료)\n\n" + "① 다음 각 호의 어느 하나에 해당하는 자에게는 2천만원 이하의 과태료를 부과한다.\n" +
                "제49조제1항을 위반하여 이륜자동차번호판을 붙이지 아니하고 이륜자동차를 운행한 자");
        List<String> topic6 = new ArrayList<>();
        topic6.add(" 도로 교통법 제78조(벌칙)]\n\n 다음 각 호의 어느 하나에 해당하는 자는 10년 이하의 징역 또는 1억원 이하의 벌금에 처한다. <개정 2011. 5. 24., 2015. 1. 6., 2015. 12. 29.>\n" +
                "\n" +
                "1. 제31조제1항(제52조에서 준용하는 경우를 포함한다)을 위반하여 결함을 은폐ㆍ축소 또는 거짓으로 공개하거나 결함사실을 안 날부터 지체 없이 그 결함을 시정하지 아니한 자\n" +
                "\n" +
                "2. 제71조제1항을 위반하여 자동차등록증 등을 위조ㆍ변조한 자 또는 부정사용한 자와 위조ㆍ변조 된 것을 매매, 매매 알선, 수수(收受) 또는 사용한 자");
        topicList.put(chapterList.get(0),topic4);
        topicList.put(chapterList.get(1),topic5);
        topicList.put(chapterList.get(2),topic6);


    }





}