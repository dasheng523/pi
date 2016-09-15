package ys.drvier;

import com.pi4j.io.gpio.*;
import ys.component.Led;

/**
 * Created by yesheng on 2016/9/12.
 */
public class LedDriver {
    private final GpioController gpio = GpioFactory.getInstance();

    public Led create(int channel) {
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(PinFactory.getPin(channel), PinState.LOW);
        pin.setShutdownOptions(true, PinState.LOW);

        Led led = new Led();
        led.setPin(pin);
        return led;
    }

    // 开灯
    public void light(Led led) {
        led.getPin().high();
    }

    // 关灯
    public void douse(Led led) {
        led.getPin().low();
    }

    public void destory(Led led) {
        led.getPin().low();
        led.setPin(null);
    }
}
