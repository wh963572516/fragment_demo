package fragmentdemo.com.fragmentdemo.SwipeRefreshLayoutDemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fragmentdemo.com.fragmentdemo.R;

/**
 * @author：Administrator
 * @time 2016/3/19 11:55
 */
public class SwipeMainActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private SwipeRecycleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_swipefresh);
        initView();
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sp_layout);
        mRecycleView = (RecyclerView) findViewById(R.id.recycleview);
        //设置圆形进度条背景颜色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置圆形进度条颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_blue_light, android.R.color.holo_orange_light);
        //调整进度条距离屏幕顶部的距离
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecycleView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));

        mAdapter = new SwipeRecycleAdapter(this);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addOnScrollListener(new MyOnScrollChangeListener());

        mAdapter.setOnItemClickListener(new RecycleViewOnItemClickListener());
        mSwipeRefreshLayout.setOnRefreshListener(new MySwipeRefreshLayout());
    }


    /**
     * @author Administrator
     * @des 下拉刷新
     * @time 2016/3/19 16:02
     **/
    private class MySwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<String> data = new ArrayList<>();
                    mAdapter.addItem(data);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 5000);

        }
    }

    private void requestData() {
        Log.d("wangheng", "上滑正在请求:");
        mRequestFlag = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> newDatas = new ArrayList<>();
                for (int i = 0, j = 10; i < j; i++) {
                    newDatas.add("item" + i);
                }
                mAdapter.addMoreItem(newDatas);
                mRequestFlag = false;
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 5000);
    }

    /**
     * @author Administrator
     * @des recycleview的item单击监听
     * @time 2016/3/19 16:01
     **/
    class RecycleViewOnItemClickListener implements IRecycleItemClickListener {

        @Override
        public void itemClickListener(View view, String str) {
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @author Administrator
     * @des 滑动监听
     * @time 2016/3/19 16:01
     **/
    private boolean mRequestFlag;
    private class MyOnScrollChangeListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (mAdapter.getItemCount() == (lastItemPosition + 1)) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    if(mRequestFlag){
                        return;
                    }
                    requestData();
                }
            }
        }


    }
}
