package comqq.example.asus_pc.news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import comqq.example.asus_pc.news.R;
import comqq.example.asus_pc.news.adapter.ViewPagerAdapter;
import comqq.example.asus_pc.news.impl.RecyclerViewItemonclick;
import comqq.example.asus_pc.news.ui.fragment.FragmentNews;

public class MainActivity extends AppCompatActivity implements RecyclerViewItemonclick{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;
    private List<Fragment> listFragment;
    private ViewPagerAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        listFragment = new ArrayList<>();
        list = new ArrayList<>();
        list.add("头条");
        list.add("社会");
        list.add("国内");
        list.add("国际");
        list.add("娱乐");
        list.add("体育");
        list.add("军事");
        list.add("科技");
        list.add("财经");
        list.add("时尚");
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i = 0; i < list.size(); i++) {
            tabLayout.addTab(tabLayout.newTab());
            FragmentNews a = new FragmentNews();
            a.setTitle(list.get(i));
            listFragment.add(a);
        }
        myAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list, listFragment);
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onItemClick(View view, int position,String html) {
        Intent intent=new Intent(MainActivity.this,TbsActivity.class);
        intent.putExtra("html",html);
        startActivity(intent);
    }
}
