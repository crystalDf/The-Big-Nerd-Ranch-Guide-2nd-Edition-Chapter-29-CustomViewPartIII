package com.star.customviewpartiii;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyListView mMyListView;
    private MyAdapter mMyAdapter;

    private List<String> contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        mMyListView = (MyListView) findViewById(R.id.my_list_view);
        mMyListView.setOnDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                mMyAdapter.notifyDataSetChanged();
            }
        });

        mMyAdapter = new MyAdapter(this, R.layout.my_list_view_item, contentList);

        mMyListView.setAdapter(mMyAdapter);
    }

    private void initList() {
        contentList = new ArrayList<>();

        contentList.add("Content Item 1");
        contentList.add("Content Item 2");
        contentList.add("Content Item 3");
        contentList.add("Content Item 4");
        contentList.add("Content Item 5");
        contentList.add("Content Item 6");
        contentList.add("Content Item 7");
        contentList.add("Content Item 8");
        contentList.add("Content Item 9");
        contentList.add("Content Item 10");
        contentList.add("Content Item 11");
        contentList.add("Content Item 12");
        contentList.add("Content Item 13");
        contentList.add("Content Item 14");
        contentList.add("Content Item 15");
        contentList.add("Content Item 16");
        contentList.add("Content Item 17");
        contentList.add("Content Item 18");
        contentList.add("Content Item 19");
        contentList.add("Content Item 20");
    }

}
