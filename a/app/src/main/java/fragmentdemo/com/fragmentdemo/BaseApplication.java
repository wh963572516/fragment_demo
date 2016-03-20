package fragmentdemo.com.fragmentdemo;

import android.app.Application;

/**
 * @author：Administrator
 * @time 2016/3/18 21:29
 */
public class BaseApplication extends Application{
    private static BaseApplication mAppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }

    public static synchronized BaseApplication getInstance(){
        return mAppContext;
    }
}
