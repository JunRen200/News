package comqq.example.asus_pc.news.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import comqq.example.asus_pc.news.R;

/**
 * Created by asus-pc on 2017/5/17.
 */

public class TbsActivity extends AppCompatActivity {
    private WebView mwebView;
    private Toolbar toolbar;
    private WebSettings settings;
    private WebSettings.TextSize textsize;
    private int state=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu_back);
        }
        initView();
    }

    private void initView() {
        mwebView = (WebView) findViewById(R.id.webview);
        String url = getIntent().getStringExtra("html");
        mwebView.loadUrl(url);
        settings = mwebView.getSettings();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(state==1) {
            menu.clear();
            getMenuInflater().inflate(R.menu.menu, menu);
        }else if(state==0){
            menu.clear();
            getMenuInflater().inflate(R.menu.menu_off,menu);
        }
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
//        if(state==1) {
//            menu.clear();
//            getMenuInflater().inflate(R.menu.menu, menu);
//        }else if(state==0){
//            menu.clear();
//            getMenuInflater().inflate(R.menu.menu_off,menu);
//        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            break;
            case R.id.item_txtsize:
                showMultiChoiceDialog();
                break;
            case R.id.item_shoucang:
                if(state==1){
                    state=0;
                }else {
                    state=1;
                }
                supportInvalidateOptionsMenu();
                break;
        }
        return true;
    }

    private void showMultiChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("正文字体");
        /**
         * 设置内容区域为多选列表项
         */
        final String[] items = {"特大号字体", "大号字体", "中号字体", "小号字体"};
        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        textsize = WebSettings.TextSize.LARGEST;
                        break;
                    case 1:
                        textsize = WebSettings.TextSize.LARGER;
                        break;
                    case 2:
                        textsize = WebSettings.TextSize.NORMAL;
                        break;
                    case 3:
                        textsize = WebSettings.TextSize.SMALLEST;
                        break;
                }

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                settings.setTextSize(textsize);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
