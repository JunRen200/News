package comqq.example.asus_pc.news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;
import java.util.List;

import comqq.example.asus_pc.news.R;
import comqq.example.asus_pc.news.adapter.ViewPagerAdapter;
import comqq.example.asus_pc.news.impl.BaseUiListener;
import comqq.example.asus_pc.news.ui.fragment.MeFragment;
import comqq.example.asus_pc.news.ui.fragment.NewsFragment;
import comqq.example.asus_pc.news.ui.fragment.WeatherFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Fragment> listFragment;
    private ViewPagerAdapter myAdapter;
    private NewsFragment newsFragment;
    private WeatherFragment weatherFragment;
    private MeFragment meFragment;
    private BottomBar bottomBar;
    private BaseUiListener baseUiListener=new BaseUiListener();
    private Tencent mTencent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initVIew();
        mTencent = Tencent.createInstance("1106189754", MainActivity.this);
        if (!mTencent.isSessionValid()) {
            //开始qq授权登录
            Log.e("AAA","开始qq授权登录");
            mTencent.login(MainActivity.this, "all", new BaseUiListener());

        }
    }

    private void initFragment() {
        listFragment = new ArrayList<>();
        newsFragment = new NewsFragment();
        weatherFragment = new WeatherFragment();
        meFragment = new MeFragment();
        listFragment.add(newsFragment);
        listFragment.add(weatherFragment);
        listFragment.add(meFragment);
    }

    private void initVIew() {
        viewPager = (ViewPager) findViewById(R.id.viewPager_main);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        myAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragment);
        viewPager.setAdapter(myAdapter);
        viewPager.setOnTouchListener(null);
//        viewPager.setOffscreenPageLimit(4);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_news:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_weather:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_me:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//               BottomBarTab bottomBarTab = bottomBar.getTabAtPosition(position);
//                bottomBarTab.setDrawingCacheBackgroundColor(Color.GRAY);
                if (positionOffset!=0) {
                    bottomBar.setActiveTabAlpha(1);
                    Log.e("AAA", "position:" + position + "     positionOffset:" + positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("AAA", position + "");
                bottomBar.selectTabAtPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Log.e("AAA","回调");
        mTencent.onActivityResultData(requestCode,resultCode,data,new BaseUiListener());

    }
}
