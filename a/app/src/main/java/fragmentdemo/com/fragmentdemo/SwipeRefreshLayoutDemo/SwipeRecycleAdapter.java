package fragmentdemo.com.fragmentdemo.SwipeRefreshLayoutDemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fragmentdemo.com.fragmentdemo.R;

/**
 * @author：Administrator
 * @time 2016/3/19 15:34
 */
public class SwipeRecycleAdapter extends RecyclerView.Adapter<SwipeRecycleAdapter.MyViewHolder> implements View.OnClickListener {
    private final Context mContext;
    private List<String> mTitles;
    private IRecycleItemClickListener mOnItemClickListener;

    public SwipeRecycleAdapter(Context context) {
        this.mContext = context;
        this.mTitles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mTitles.add("item" + i);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mTitles.get(position));
        holder.itemView.setTag(mTitles.get(position));


    }


    @Override
    public int getItemCount() {
        return mTitles != null ? mTitles.size() : 0;
    }

    //添加数据
    public void addItem(List<String> datas) {
        datas.addAll(mTitles);
        mTitles.clear();
        mTitles.addAll(datas);
        Log.d("wangheng","数量:"+mTitles.size());
        notifyDataSetChanged();
    }

    public void addMoreItem(List<String> newDatas) {
        mTitles.addAll(newDatas);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.itemClickListener(v,(String)v.getTag());
        }
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }


    public void setOnItemClickListener(IRecycleItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
}

