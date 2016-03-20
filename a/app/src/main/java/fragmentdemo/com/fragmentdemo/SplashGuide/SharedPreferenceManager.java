package fragmentdemo.com.fragmentdemo.SplashGuide;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import fragmentdemo.com.fragmentdemo.BaseApplication;

/**
 * @author：Administrator
 * @time 2016/3/18 21:24
 */
public class SharedPreferenceManager {
    private static SharedPreferenceManager mInstance;
    private SharedPreferences.Editor editor;
    private SharedPreferences mSp;

    private SharedPreferenceManager() {
    }

    public static SharedPreferenceManager getInstance() {
        if (mInstance == null) {
            synchronized (SharedPreferenceManager.class) {
                if (mInstance == null) {
                    mInstance = new SharedPreferenceManager();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        mSp = BaseApplication.getInstance().getSharedPreferences("SP_NAME", Context.MODE_PRIVATE);
        if (mSp != null) {
            editor = mSp.edit();
        }
    }

    public void putInt(String key, int value) {
        try {
            editor.putInt(key, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getInt(String key, int def) {
        return mSp.getInt(key, def);
    }

    public void putFloat(String key, float value) {
        try {
            editor.putFloat(key, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getFloat(String key, float def) {
        return mSp.getFloat(key, def);
    }

    public void putLong(String key, long value) {
        try {
            editor.putLong(key, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getLong(String key, long def) {
        return mSp.getLong(key, def);
    }


    public void putBoolean(String key, boolean value) {
        try {
            editor.putBoolean(key, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getBoolean(String key, boolean def) {
        return mSp.getBoolean(key, def);
    }

    public void putString(String key, String value) {
        try {
            if (key == null) {
                return;
            }
            editor.putString(key, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String key, String def) {
        return mSp.getString(key, def);
    }

    public Map<String, ?> getAll() {
        return mSp.getAll();
    }


}
