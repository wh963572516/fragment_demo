package fragmentdemo.com.fragmentdemo.commonadapter.common;

/**
 * @author：Administrator
 * @time 2016/3/17 21:41
 */
public class TCast {

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }
}
