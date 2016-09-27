package ys.driver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

import ys.component.Led;
import ys.drvier.TestDriver;
import ys.drvier.impl.LedDriverImpl;

/**
 * Created by yesheng on 2016/9/15.
 */
public class TestLedDriver {
    private final Logger logger = Logger.getLogger(TestLedDriver.class);

    public void testLed() throws InterruptedException {
        LedDriverImpl ledDriver = new LedDriverImpl();
        Led led = ledDriver.create(1);
        ledDriver.light(led);
        boolean isHigh = led.getPin().isHigh();
        Thread.sleep(3000);
        ledDriver.douse(led);
        boolean isLow = led.getPin().isLow();
        assertTrue(isHigh && isLow);
    }

    @Test
    public void testGuice() {
        Injector inj=  Guice.createInjector();
        TestDriver testDriver = inj.getInstance(TestDriver.class);
        boolean isTrue = testDriver.test();
        assertTrue(isTrue);
    }
}
