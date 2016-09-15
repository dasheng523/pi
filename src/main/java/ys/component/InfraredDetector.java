package ys.component;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

/**
 * Created by yesheng on 2016/9/12.
 * 红外检测模块
 */
public class InfraredDetector{
    private GpioPinDigitalInput inPin;
    private GpioPinDigitalOutput outPin;

    public GpioPinDigitalOutput getOutPin() {
        return outPin;
    }

    public void setOutPin(GpioPinDigitalOutput outPin) {
        this.outPin = outPin;
    }

    public GpioPinDigitalInput getInPin() {
        return inPin;
    }

    public void setInPin(GpioPinDigitalInput inPin) {
        this.inPin = inPin;
    }
}
