package ys.driver;

import org.apache.log4j.Logger;
import org.junit.Test;
import ys.component.Ultrasound;
import ys.drvier.UltrasoundDriver;
import ys.drvier.impl.UltrasoundDriverImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Created by yesheng on 2016/9/25.
 */
public class TestUltrasoundDriver {

    private final Logger logger = Logger.getLogger(TestUltrasoundDriver.class);

    @Test
    public void testDriver() throws InterruptedException, ExecutionException, TimeoutException {
        UltrasoundDriver ultrasoundDriver = new UltrasoundDriverImpl();
        Ultrasound ul = ultrasoundDriver.create(1, 2);
        Double distance = 0D;
        while (true) {
            distance = ultrasoundDriver.measureDistance(ul);
            if (distance == 0){
                break;
            }
            logger.info(distance);
            Thread.sleep(1000);
        }

    }
}
