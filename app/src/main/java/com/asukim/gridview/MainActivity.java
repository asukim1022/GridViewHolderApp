package com.asukim.gridview;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

/**
 *@MainActivity
 *@brief listView생성, adapter생성, ArrayList제어, 데이터 저장/불러오기
 *@details
 */
public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    //GridView 관리하는 adapter
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);

        /*
        adapter 생성후 layout이랑 배열 연결
        adapter : adapter 설정, MyListViewAdapter에 layout.item와 myList 연결
        */
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        //listView item 클릭시 이벤트
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //해당 클릭 position 출력
                Toast.makeText(getApplicationContext(), position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /** @brief getData
     *  @detail ImageItem에 데이터 입력후 list item 생성
     */
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, "img : " + i));
        }
        return imageItems;
    }
}