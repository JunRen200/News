package comqq.example.asus_pc.news.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;

import comqq.example.asus_pc.news.R;
import comqq.example.asus_pc.news.impl.BaseUiListener;


/**
 * Created by asus-pc on 2017/5/31.
 */

public class MeFragment extends Fragment {
    private RelativeLayout qq;
    private RelativeLayout weixing;
    private RelativeLayout xinlang;
    private Tencent mTencent;
    private Button btn_logout;
    private UserInfo userInfo; //qq用户信息
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        qq= (RelativeLayout) view.findViewById(R.id.qq);
        weixing= (RelativeLayout) view.findViewById(R.id.weixin);
        xinlang= (RelativeLayout) view.findViewById(R.id.xinlang);
        btn_logout= (Button) view.findViewById(R.id.btn_logou);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                Log.e("AAA","注销");
            }
        });
    }
    private void login() {
        //如果session无效，就开始登录
        mTencent = Tencent.createInstance("1106189754", getActivity());
        if (!mTencent.isSessionValid()) {
            //开始qq授权登录
            Log.e("AAA","开始qq授权登录");
            mTencent.login(getActivity(), "all", new BaseUiListener());

        }
    }
    public void logout()
    {
        mTencent.logout(getActivity());
    }
}
