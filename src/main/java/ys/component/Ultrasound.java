package ys.component;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

/**
 * Created by yesheng on 2016/9/25.
 */
public class Ultrasound {
    private GpioPinDigitalInput echoPin;
    private GpioPinDigitalOutput trigPin;

    public GpioPinDigitalInput getEchoPin() {
        return echoPin;
    }

    public void setEchoPin(GpioPinDigitalInput echoPin) {
        this.echoPin = echoPin;
    }

    public GpioPinDigitalOutput getTrigPin() {
        return trigPin;
    }

    public void setTrigPin(GpioPinDigitalOutput trigPin) {
        this.trigPin = trigPin;
    }
}
