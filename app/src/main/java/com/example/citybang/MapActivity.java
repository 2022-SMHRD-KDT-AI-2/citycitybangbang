package com.example.citybang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;



public class MapActivity extends AppCompatActivity {

    Button btnMapSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // 툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        //MapView mapView = new MapView(this);
        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);

        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.11051782214, 126.87720413169), true);
        // 줌 레벨 변경
        mapView.setZoomLevel(3, true);
//        // 줌 인
//        mapView.zoomIn(true);
//        // 줌 아웃
//        mapView.zoomOut(true);

        //마커 찍기
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(35.11051782214, 126.87720413169);

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("현재위치");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);


        mapViewContainer.addView(mapView);

        Intent intent = new Intent(MapActivity.this,SearchActivity.class);
        btnMapSearch = findViewById(R.id.btnMapSearch);
        btnMapSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
//        binding.mapView.CurrentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading;



    }



}