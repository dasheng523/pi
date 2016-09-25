package ys.drvier.impl;

import com.google.inject.Singleton;
import com.pi4j.io.gpio.*;
import ys.common.SystemResource;
import ys.component.Ultrasound;
import ys.drvier.DriverException;
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

    @Override
    public Ultrasound create(int trigChannel, int echoChannel) {
        GpioPinDigitalInput inPin = gpio.provisionDigitalInputPin(PinFactory.getPin(echoChannel), PinPullResistance.PULL_UP);
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
        // 发送测距信号给模块
        ultrasound.getTrigPin().pulse(1);

        // 异步监听事件
        Future<Double> future = SystemResource.ExecutorService.submit(() -> {
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

            if (start > end) {
                return 0D;
            }

            return (end - start) / 1000 * 340 / 2.0D;
        });

        //设置超时时间为1500毫秒
        return future.get(1500, TimeUnit.MILLISECONDS);
    }
}
