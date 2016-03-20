package fragmentdemo.com.fragmentdemo.commonadapter.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author：Administrator
 * @time 2016/3/17 21:16
 * 初步抽取adapter
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {
    public List<T> mDatas;
    public Context mContext;
    public LayoutInflater mLayoutInflater;
    private int mLayoutId;
    public CommonBaseAdapter(Context context, List<T> datas,int layoutId) {
        mContext = context;
        mDatas = datas;
        mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BaseViewHolder viewHolder = BaseViewHolder.get(mContext, convertView, parent, mLayoutId);
        convert(getItem(position), viewHolder);
        return viewHolder.getConvertView();
    }

    public abstract void convert(T item, BaseViewHolder viewHolder);

}
