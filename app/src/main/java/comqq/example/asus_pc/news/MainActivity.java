package comqq.example.asus_pc.news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;
    private List<Fragment> listFragment;
    private viewPagerAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        listFragment=new ArrayList<>();
        list = new ArrayList<>();
        list.add("头条");
        list.add("视频");
        list.add("娱乐");
        list.add("体育");
        list.add("科技");
        list.add("财经");
        list.add("汽车");
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i=0;i<list.size();i++) {
            tabLayout.addTab(tabLayout.newTab());
            listFragment.add(new fragment());
        }
        myAdapter=new viewPagerAdapter(getSupportFragmentManager(),list,listFragment);
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
