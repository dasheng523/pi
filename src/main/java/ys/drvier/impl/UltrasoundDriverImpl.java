package ys.drvier.impl;

import com.google.inject.Singleton;
import com.pi4j.io.gpio.*;
import org.apache.log4j.Logger;
import ys.common.SystemResource;
import ys.component.Ultrasound;
import ys.drvier.PinFactory;
import ys.drvier.UltrasoundDriver;

import java.util.concurrent.*;

/**
 * Created by yesheng on 2016/9/25.
 * 超声波模块
 */
@Singleton
public class UltrasoundDriverImpl implements UltrasoundDriver {
    private final GpioController gpio = GpioFactory.getInstance();
    private final Logger logger = Logger.getLogger(UltrasoundDriverImpl.class);

    @Override
    public Ultrasound create(int trigChannel, int echoChannel) {
        GpioPinDigitalInput inPin = gpio.provisionDigitalInputPin(PinFactory.getPin(echoChannel), PinPullResistance.PULL_DOWN);
        GpioPinDigitalOutput outPin = gpio.provisionDigitalOutputPin(PinFactory.getPin(trigChannel), PinState.LOW);
        outPin.setShutdownOptions(true, PinState.LOW);
        inPin.setShutdownOptions(true, PinState.LOW);

        Ultrasound ultrasound = new Ultrasound();
        ultrasound.setEchoPin(inPin);
        ultrasound.setTrigPin(outPin);

        return ultrasound;
    }

    @Override
    public void destroy(Ultrasound ultrasound) {
        ultrasound.setTrigPin(null);
        ultrasound.setEchoPin(null);
    }

    @Override
    public double measureDistance(Ultrasound ultrasound) throws InterruptedException, ExecutionException, TimeoutException {

        // 异步事件
        Future<Double> future = SystemResource.ExecutorService.submit(() -> {
            logger.info("监听测距返回信号");
            while (true) {
                if (ultrasound.getEchoPin().getState() == PinState.HIGH) {
                    break;
                }
            }

            long start = System.currentTimeMillis();

            while (true) {
                if (ultrasound.getEchoPin().getState() == PinState.LOW) {
                    break;
                }
            }
            long end = System.currentTimeMillis();

            if (start >= end) {
                return 0D;
            }
            return ((end - start) / 1000D * 340) / 2;

        });

        // 发送测距信号给模块
        logger.info("发送测距信号");
        ultrasound.getTrigPin().pulse(1);

        // 设置超时时间
        return future.get(3000, TimeUnit.MILLISECONDS);
    }
}
