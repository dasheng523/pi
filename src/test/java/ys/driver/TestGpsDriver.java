package ys.driver;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ys.component.Gps;
import ys.drvier.impl.GpsDriverImpl;


/**
 * Created by yesheng on 2016/9/30.
 */
public class TestGpsDriver {
    private final Logger logger = LoggerFactory.getLogger(TestGpsDriver.class);

    public void testtest() {
        try {
            GpsDriverImpl gd = new GpsDriverImpl();
            Gps gps = gd.create();
            Thread.sleep(30000);
            if (gps != null) {
                logger.info(gps.getPositionData().toString());
                gd.destroy(gps);
            }
        } catch (Exception e) {
            logger.error("异常", e.getStackTrace());
        }

    }
}
