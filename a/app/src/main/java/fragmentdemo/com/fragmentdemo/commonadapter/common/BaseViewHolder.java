package fragmentdemo.com.fragmentdemo.commonadapter.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author：Administrator
 * @time 2016/3/17 21:23
 */
public class BaseViewHolder {
    private SparseArray<View> mViews;

    private View mConvertView;
    private Context mContext;

    public BaseViewHolder(Context context, ViewGroup parent, int layoutId) {
        this.mContext = context;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mViews = new SparseArray<>();
        mConvertView.setTag(this);
    }


    /**
     * 获取holder对象
     *
     * @param context
     * @param parent
     * @param layoutId
     * @return
     */
    public static BaseViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new BaseViewHolder(context, parent, layoutId);
        }
        return (BaseViewHolder) convertView.getTag();
    }


    /**
     * 根据控件id获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return TCast.cast(view);
    }

    public View getConvertView() {
        return mConvertView;
    }

    public BaseViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public BaseViewHolder setImgByUrl(int viewId, String url) {
        ImageView imgView = getView(viewId);
        //这里用imageloader加载
        return this;
    }

    public BaseViewHolder setImgByResource(int viewId, int resId) {
        ImageView imgView = getView(viewId);
        imgView.setImageResource(resId);
        return this;
    }

    public BaseViewHolder setImgByBitmap(int viewId, Bitmap bitmap) {
        ImageView imgView = getView(viewId);
        imgView.setImageBitmap(bitmap);
        return this;
    }


}
