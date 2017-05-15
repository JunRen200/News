package comqq.example.asus_pc.news;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by asus-pc on 2017/5/15.
 */

public class SPUtil {
    public static boolean getIsFirstRun(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isFirst = sharedPreferences.getBoolean("isFirst", true);
        return isFirst;
    }

    public static void setIsFirstRun(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirst", false);
        editor.apply();
    }

}
