package ys.component;

import com.pi4j.io.serial.Serial;
import ys.entity.PositionData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yesheng on 2016/10/8.
 */
public class Gps {
    private Serial serial;
    private String tempData;
    private List<String> list = new ArrayList<>();
    private PositionData positionData;


    public String getList() {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void insertList(String info) {
        list.add(info);
    }

    public String getTempData() {
        return tempData;
    }

    public void setTempData(String tempData) {
        this.tempData = tempData;
    }

    public Serial getSerial() {
        return serial;
    }

    public void setSerial(Serial serial) {
        this.serial = serial;
    }

    public void setPositionData(PositionData positionData) {
        this.positionData = positionData;
    }

    public PositionData getPositionData() {
        return positionData;
    }
}
