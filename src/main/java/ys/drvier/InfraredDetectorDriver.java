package ys.drvier;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ys.component.InfraredDetector;

import java.util.function.Consumer;

/**
 * Created by yesheng on 2016/9/12.
 * 红外检测模块
 */
public class InfraredDetectorDriver{
    private final GpioController gpio = GpioFactory.getInstance();
    private final Logger logger = LoggerFactory.getLogger(InfraredDetectorDriver.class);

    public InfraredDetector create(int powerChannel, int dataChannel) {
        GpioPinDigitalOutput outPin = gpio.provisionDigitalOutputPin(PinFactory.getPin(powerChannel), PinState.LOW);
        PinPullResistance pull = PinPullResistance.PULL_DOWN;
        GpioPinDigitalInput inPin = gpio.provisionDigitalInputPin(PinFactory.getPin(dataChannel), pull);

        inPin.setShutdownOptions(true, PinState.LOW);
        outPin.setShutdownOptions(true);

        InfraredDetector infraredDetector = new InfraredDetector();
        infraredDetector.setInPin(inPin);
        infraredDetector.setOutPin(outPin);

        return infraredDetector;
    }

    public void destory(InfraredDetector infraredDetector) {
        infraredDetector.getOutPin().low();
        infraredDetector.setOutPin(null);
        infraredDetector.setInPin(null);
    }

    public void listen(InfraredDetector infraredDetector, final Consumer<Boolean> bodyNearHandler) {
        infraredDetector.getInPin().addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState().isHigh()) {
                    logger.info("有人来了");
                    bodyNearHandler.accept(true);
                }
                else {
                    logger.info("人走了");
                    bodyNearHandler.accept(false);
                }
            }
        });
    }

}
