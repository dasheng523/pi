package ys.drvier.impl;

import com.google.inject.Singleton;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import ys.component.InfraredDetector;
import ys.drvier.InfraredDetectorDriver;
import ys.drvier.PinFactory;

import java.util.function.Consumer;

/**
 * Created by yesheng on 2016/9/12.
 * 红外检测模块
 */
@Singleton
public class InfraredDetectorDriverImpl implements InfraredDetectorDriver{
    private final GpioController gpio = GpioFactory.getInstance();

    public InfraredDetector create(int dataChannel) {
        PinPullResistance pull = PinPullResistance.PULL_UP;
        GpioPinDigitalInput inPin = gpio.provisionDigitalInputPin(PinFactory.getPin(dataChannel), pull);

        inPin.setShutdownOptions(true, PinState.LOW);

        InfraredDetector infraredDetector = new InfraredDetector();
        infraredDetector.setInPin(inPin);

        return infraredDetector;
    }

    public void destroy(InfraredDetector infraredDetector) {
        infraredDetector.getOutPin().low();
        infraredDetector.setOutPin(null);
        infraredDetector.setInPin(null);
    }

    public void listen(InfraredDetector infraredDetector, final Consumer<Boolean> bodyNearHandler) {

        infraredDetector.getInPin().addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState().isHigh()) {
                    bodyNearHandler.accept(true);
                }
                else {
                    bodyNearHandler.accept(false);
                }
            }
        });

    }

}
