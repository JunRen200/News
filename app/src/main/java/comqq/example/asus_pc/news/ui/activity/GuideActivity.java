package comqq.example.asus_pc.news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comqq.example.asus_pc.news.R;

/**
 * Created by asus-pc on 2017/5/15.
 */

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView txt;
    //导航页资源
    private int[] images = new int[]{
            R.mipmap.foods1,
            R.mipmap.foods2,
            R.mipmap.car,
    };
    //圆点与圆点之间的边距
    private int left;
    //用来存放导航图片实例（保证唯一性，滑动的时候不重复创建）
    private List<ImageView> imageViews;
    //存放三个灰色圆点的线性布局
    private LinearLayout ll;
    //用来存放红色圆点和灰色圆点的相对布局
    private RelativeLayout rl;
    //红色圆点ImageView
    private ImageView red_Iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);  //无title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        setContentView(R.layout.activity_guide);
        initView();
        //初始化导航页面和灰色圆点
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(GuideActivity.this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setImageResource(images[i]);
            imageViews.add(iv);
            //动态加载灰色圆点
            ImageView gray_Iv = new ImageView(this);
            gray_Iv.setImageResource(R.mipmap.circle1);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            //从第二个开始有边距
            if (i > 0) {
                layoutParams.leftMargin = 20;  //注意单位是px
            }
            gray_Iv.setLayoutParams(layoutParams);
            ll.addView(gray_Iv);
        }
        red_Iv = new ImageView(this);
        red_Iv.setImageResource(R.mipmap.circle);
        rl.addView(red_Iv);
        //任何一个组件都可以得到视图树
        red_Iv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //视图完成绘制的时候调用
            @Override
            public void onGlobalLayout() {
                left = ll.getChildAt(1).getLeft() - ll.getChildAt(0).getLeft();
                //移除视图树的监听
                red_Iv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        //为ViewPager添加适配器
        viewPager.setAdapter(new MyAdapterGui());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //导航页被选择的时候调用
            @Override
            public void onPageSelected(int position) {
                if (position == images.length - 1) {
                    txt.setVisibility(View.VISIBLE);
                } else {
                    txt.setVisibility(View.GONE);
                }
            }

            //导航页滑动的时候调用
            //positionOffset:滑动的百分比（[0,1}）
            @Override
            public void onPageScrolled(int position, float positionOffset, int arg2) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) red_Iv.getLayoutParams();
                layoutParams.leftMargin = (int) (left * positionOffset + position * left);
                red_Iv.setLayoutParams(layoutParams);
            }

            //导航页滑动的状态改变的时候调用
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    //初始化组件
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imageViews = new ArrayList<ImageView>();
        ll = (LinearLayout) findViewById(R.id.ll);
        rl = (RelativeLayout) findViewById(R.id.rl);
        txt = (TextView) findViewById(R.id.txt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    //PagerAdapter有四个方法
    class MyAdapterGui extends PagerAdapter {
        //返回导航页的个数
        @Override
        public int getCount() {
            return images.length;
        }

        //判断是否由对象生成
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //加载页面
        //ViewGroup:父控件指ViewPager
        //position:当前子控件在父控件中的位置
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            container.addView(iv);
            return iv;
        }

        //移除页面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}


