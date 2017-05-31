package comqq.example.asus_pc.news.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus-pc on 2017/3/16.
 */

public class Forecast {
    public String date;
    public Tmp tmp;
    public Cond cond;

    public class Tmp {
        public String max;
        public String min;
    }

    public class Cond {
        @SerializedName("txt_d")
        public String info;
    }
}

