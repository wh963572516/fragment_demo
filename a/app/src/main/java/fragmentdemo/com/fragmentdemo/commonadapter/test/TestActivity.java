package fragmentdemo.com.fragmentdemo.commonadapter.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fragmentdemo.com.fragmentdemo.R;

/**
 * @author：Administrator
 * @time 2016/3/17 22:11
 */
public class TestActivity extends Activity {
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_adpater);
        ListView listView = (ListView) findViewById(R.id.listview);

        initData();
        MyAdapter adapter = new MyAdapter<>(getApplicationContext(), mDatas, R.layout.layout_item);
        listView.setAdapter(adapter);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add(i + "wangheng");
        }
    }
}
