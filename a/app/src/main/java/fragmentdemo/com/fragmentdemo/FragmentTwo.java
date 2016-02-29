package fragmentdemo.com.fragmentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/2/18.
 */
public class FragmentTwo extends Fragment implements View.OnClickListener {
    private FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        Button btn = (Button) view.findViewById(R.id.btn_get);
        Button btn2 = (Button)view.findViewById(R.id.btn_send);
        manager = getFragmentManager();
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                FragmentOne fragmentOne = (FragmentOne) manager.findFragmentByTag(ConstantUtil.FRAGMENT1_TAG);
                TextView textView = (TextView) fragmentOne.getView().findViewById(R.id.tv_f1);
                Toast.makeText(getActivity(), textView.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_send:
                if(mListener != null){
                    mListener.sendDataToActivity("我是来自fragment2的数据");
                }
                break;
            default:
                break;
        }


    }

    private ISendListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            if(activity instanceof ISendListener){
                mListener = (ISendListener)activity;
            }
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement ISendListener");
        }
    }
}
