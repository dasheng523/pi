package ys.component;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

/**
 * Created by yesheng on 2016/9/12.
 */
public class Led{

    private GpioPinDigitalOutput pin;

    public Led() {

    }

    public GpioPinDigitalOutput getPin() {
        return pin;
    }

    public void setPin(GpioPinDigitalOutput pin) {
        this.pin = pin;
    }





}
