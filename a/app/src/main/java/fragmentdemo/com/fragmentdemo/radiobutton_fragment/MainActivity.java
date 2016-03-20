package fragmentdemo.com.fragmentdemo.radiobutton_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import fragmentdemo.com.fragmentdemo.R;

public class MainActivity extends AppCompatActivity implements ISendListener {
    private FragmentManager fragmentManager;
    private String mSaveTag;
    private FragmentOne f1;
    private FragmentTwo f2;
    private RadioGroup mRadioGroup;
    private RadioButton rb1;
    private RadioButton rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("wanghengfragment", "Activity---->onCreate");
        setContentView(R.layout.activity_main);

        initView();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            f1 = (FragmentOne) fragmentManager.findFragmentByTag(ConstantUtil.FRAGMENT1_TAG);
            f2 = (FragmentTwo) fragmentManager.findFragmentByTag(ConstantUtil.FRAGMENT2_TAG);
            mSaveTag = savedInstanceState.getString("currentTag");
            Log.d("wanghengfragment", "保存的tag:" + mSaveTag);
        }

        if (f1 == null) {
            f1 = new FragmentOne();
        }
        if (f2 == null) {
            f2 = new FragmentTwo();
        }

        if (mSaveTag != null && mSaveTag.equals(ConstantUtil.FRAGMENT2_TAG)) {
            rb2.setChecked(true);
            switchFragment(f2);
        } else {
            rb1.setChecked(true);
            switchFragment(f1);
        }

        mRadioGroup.setOnCheckedChangeListener(new MyRadioGroupOnCheckedChangeListener());
    }

    private void switchFragment(Fragment toFragment) {
        String mTag;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fromFragment;
        if (toFragment == f1) {
            fromFragment = f2;
            mTag = ConstantUtil.FRAGMENT1_TAG;
        } else {
            fromFragment = f1;
            mTag = ConstantUtil.FRAGMENT2_TAG;
        }

        if (!toFragment.isAdded()) {
            transaction.hide(fromFragment).add(R.id.content, toFragment, mTag).show(toFragment).commit();
        } else {
            transaction.hide(fromFragment).show(toFragment).commit();
        }
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mRadioGroup = (RadioGroup) findViewById(R.id.rgTab);
        rb1 = (RadioButton) findViewById(R.id.rb_1);
        rb2 = (RadioButton) findViewById(R.id.rb_2);

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            Log.d("wanghengfragment", "Activity---->onSaveInstanceState");
            if (mRadioGroup.getCheckedRadioButtonId() == R.id.rb_1) {
                outState.putString("currentTag", ConstantUtil.FRAGMENT1_TAG);
            } else {
                outState.putString("currentTag", ConstantUtil.FRAGMENT2_TAG);
            }
        }
    }


    class MyRadioGroupOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Fragment toFragment = getFragmentByIndex(checkedId);
            switchFragment(toFragment);
        }
    }

    private Fragment getFragmentByIndex(int checkedId) {
        Fragment fragment = null;
        switch (checkedId) {
            case R.id.rb_1:
                fragment = f1;
                break;
            case R.id.rb_2:
                fragment = f2;
                break;
            default:
                break;
        }
        return fragment;
    }


    @Override
    public void sendDataToActivity(String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("wanghengfragment", "Activity---->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("wanghengfragment", "Activity---->onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("wanghengfragment", "Activity---->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("wanghengfragment", "Activity---->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("wanghengfragment", "Activity---->onDestroy");
    }
}
