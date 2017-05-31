package comqq.example.asus_pc.news.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus-pc on 2017/3/16.
 */

public class Now {
    public String tmp;
    public Cond cond;

    public class Cond {
        @SerializedName("txt")
        public String info;
    }
}
