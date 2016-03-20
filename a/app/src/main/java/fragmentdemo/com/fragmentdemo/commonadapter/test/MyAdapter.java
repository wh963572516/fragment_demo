package fragmentdemo.com.fragmentdemo.commonadapter.test;

import android.content.Context;

import java.util.List;

import fragmentdemo.com.fragmentdemo.R;
import fragmentdemo.com.fragmentdemo.commonadapter.common.BaseViewHolder;
import fragmentdemo.com.fragmentdemo.commonadapter.common.CommonBaseAdapter;

/**
 * @author：Administrator
 * @time 2016/3/17 21:45
 */
public class MyAdapter<T> extends CommonBaseAdapter {
    public MyAdapter(Context context, List<T> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }


    @Override
    public void convert(Object item, BaseViewHolder viewHolder) {
        if (item instanceof String) {
            viewHolder.setText(R.id.tv, (String) item);
        }
    }


}
