package ys.entity;

/**
 * Created by yesheng on 2016/10/11.
 */
public class PositionData {
    private String time;        // 定位时间 格式 hhmmss.ss
    private String lng;         // 经度
    private String lat;         // 纬度
    private String altitude;    // 海拔

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PositionData{" +
                "altitude='" + altitude + '\'' +
                ", time='" + time + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
