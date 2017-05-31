package comqq.example.asus_pc.news.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus-pc on 2017/3/16.
 */
public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public  String weatherId;
    public  Update update;

    public class Update {
        @SerializedName("loc")
        public  String updateTime;
    }
}
