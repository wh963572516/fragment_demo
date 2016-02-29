package fragmentdemo.com.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/2/17.
 */
public class FragmentOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("wanghengfragment","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("wanghengfragment", "onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("wanghengfragment", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("wanghengfragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("wanghengfragment", "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("wanghengfragment", "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("wanghengfragment", "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("wanghengfragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("wanghengfragment", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("wanghengfragment", "onDetach");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("wanghengfragment", "onSaveInstanceState");
    }


}
