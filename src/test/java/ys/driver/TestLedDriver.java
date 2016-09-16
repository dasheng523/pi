package ys.driver;

import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

import ys.component.Led;
import ys.drvier.LedDriver;

/**
 * Created by yesheng on 2016/9/15.
 */
public class TestLedDriver {
    private final Logger logger = Logger.getLogger(TestLedDriver.class);
    @Test
    public void testLed() throws InterruptedException {
        LedDriver ledDriver = new LedDriver();
        Led led = ledDriver.create(1);
        ledDriver.light(led);
        boolean isHigh = led.getPin().isHigh();
        Thread.sleep(3000);
        ledDriver.douse(led);
        boolean isLow = led.getPin().isLow();
        assertTrue(isHigh && isLow);
    }
}
