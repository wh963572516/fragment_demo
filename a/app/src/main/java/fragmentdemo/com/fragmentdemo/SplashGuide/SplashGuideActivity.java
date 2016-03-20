package fragmentdemo.com.fragmentdemo.SplashGuide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import fragmentdemo.com.fragmentdemo.R;
import fragmentdemo.com.fragmentdemo.SwipeRefreshLayoutDemo.SwipeMainActivity;

/**
 * @author：Administrator
 * @time 2016/3/18 20:32
 */
public class SplashGuideActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private int[] imgs = {R.mipmap.newer01, R.mipmap.newer02, R.mipmap.newer03, R.mipmap.newer04};
    private ViewPager mViewPager;
    private CirclePageIndicator mCircleIndicator;
    private Button mBtnHome;
    private Animation mFadeIn;
    private ImageView mLaunchImg;
    private RelativeLayout mRlGuideGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_guide);
        initView();

        SharedPreferenceManager.getInstance().init();
        SharedPreferenceManager sp = SharedPreferenceManager.getInstance();
        boolean firstFlag = sp.getBoolean("first_start_app", true);
        if (firstFlag) {
            initGuideGallery();
            sp.putBoolean("first_start_app", false);
        } else {
            initLaunchLogo();
        }
    }
    
    /**
    *@desc 初始化画廊
    *@author Administrator
    *@time 2016/3/18 22:17
    **/
    private void initGuideGallery() {
        mRlGuideGallery.setVisibility(View.VISIBLE);
        mLaunchImg.setVisibility(View.GONE);
        initAnim();
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mCircleIndicator = (CirclePageIndicator) findViewById(R.id.circlepage_indicator);
        ViewPagerAdapter mAdapter = new ViewPagerAdapter();
        mViewPager.setAdapter(mAdapter);
        mCircleIndicator.setViewPager(mViewPager);
        mBtnHome = (Button) findViewById(R.id.btnHome);

        initListener();
    }
    
    /**
    *@desc 初始化启动logo
    *@author Administrator
    *@time 2016/3/18 22:47
    **/
    private void initLaunchLogo() {
        mLaunchImg.setVisibility(View.VISIBLE);
        mRlGuideGallery.setVisibility(View.GONE);
    }

    private void initAnim() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

    }

    private void initListener() {
        mCircleIndicator.setOnPageChangeListener(this);
        mBtnHome.setOnClickListener(this);
    }

    private void initView() {
        mLaunchImg = (ImageView) findViewById(R.id.iv_launch);
        mRlGuideGallery = (RelativeLayout) findViewById(R.id.rl_guide_gallery);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == imgs.length - 1) {
            mBtnHome.setVisibility(View.VISIBLE);
            mBtnHome.setAnimation(mFadeIn);
        } else {
            mBtnHome.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHome:
                Intent intent = new Intent(SplashGuideActivity.this, SwipeMainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(SplashGuideActivity.this);
            imageView.setImageResource(imgs[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object instanceof View) {
                container.removeView((View) object);
            }
        }
    }
}
