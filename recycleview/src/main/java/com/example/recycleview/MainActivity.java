package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.recycleview.Bean.ItemBean;
import com.example.recycleview.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ItemBean> mData;

    private ListViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) this.findViewById(R.id.MyRecycleView);
        swipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.reflesh_layout);

        initData();
    }




    private void initListener(){
        adapter.setOnItemClickListener(new ListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //条目点击事件
                Toast.makeText(MainActivity.this,"点击的是第"+position
                +"个条目",Toast.LENGTH_SHORT).show();
            }
        });

        //上拉加载
        if(adapter instanceof ListViewAdapter){
            ((ListViewAdapter)adapter).setOnRefleshListener(new ListViewAdapter.OnReFleshListener() {

                @Override
                public void UpPullReFlesh(final ListViewAdapter.LoaderMoreHolder loaderMoreHolder) {
                    //处理下拉刷新，添加数据
                    ItemBean data = new ItemBean();
                    data.icon=1;
                    data.title="";
                    mData.add(data);
                    //更新UI
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //停止刷新，更新列表
                            adapter.notifyDataSetChanged();
                            loaderMoreHolder.update(loaderMoreHolder.LOADER_STATE_LOADING);
                        }
                    },3000);
                }
            });
        }
    }

    private void initData(){

        mData = new ArrayList<>();
        for(int i=0;i<10;i++){
            ItemBean data = new ItemBean();
            data.icon = i;
            data.title= "";
            mData.add(data);
        }

        //RecycleView需要设置样式，其实就是设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //创建适配器
        adapter = new ListViewAdapter(mData);
        //设置到 RecycleView里面
//        recyclerView.setAdapter(adapter);
//
//        initListener();
//
//        DownPullUpdate();
    }


    //处理下拉刷新
    private void  DownPullUpdate(){

        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //处理下拉刷新，添加数据
                ItemBean data = new ItemBean();
                data.icon=1;
                data.title="";
                mData.add(data);
               //更新UI
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       //停止刷新，更新列表
                       adapter.notifyDataSetChanged();
                       swipeRefreshLayout.setRefreshing(false);
                   }
               },3000);
            }
        });
    }
}