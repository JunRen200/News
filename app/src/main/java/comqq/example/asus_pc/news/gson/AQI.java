package comqq.example.asus_pc.news.gson;

/**
 * Created by asus-pc on 2017/3/16.
 */
public class AQI {
    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
