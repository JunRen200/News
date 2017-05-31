package comqq.example.asus_pc.news.impl;

import android.util.Log;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by asus-pc on 2017/5/31.
 */

public class BaseUiListener implements IUiListener {

    public BaseUiListener(){
    }
    @Override
    public void onComplete(Object o) {
        if(o == null){
            Log.e("AAA", "你好，" );
            return;
        }
        try {
            JSONObject jo = (JSONObject) o;
            int ret = jo.getInt("ret");
            String nickName = jo.getString("nickname");
            String gender = jo.getString("gender");

            Log.e("AAA", "你好，" + jo.toString());

        } catch (Exception e) {
            // TODO: handle exception

        }

    }

    @Override
    public void onError(UiError uiError) {
        Log.e("AAA", "错误");
    }

    @Override
    public void onCancel() {
        Log.e("AAA", "取消");
    }
}
