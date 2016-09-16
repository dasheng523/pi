package ys.driver;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ys.component.InfraredDetector;
import ys.drvier.InfraredDetectorDriver;

/**
 * Created by yesheng on 2016/9/16.
 */
public class TestInfraredDetectorDriver {
    private final Logger logger = LoggerFactory.getLogger(InfraredDetectorDriver.class);

    private void log(Boolean isHasBody) {
        if (isHasBody) {
            logger.info("有人来-test");
        }
        else {
            logger.info("人走了-test");
        }
    }

    @Test
    public void testListen() {
        InfraredDetectorDriver driver = new InfraredDetectorDriver();
        InfraredDetector infraredDetector = driver.create(0, 2);
        driver.listen(infraredDetector, (isHasBody) -> logger.info(isHasBody ? "有人" : "没人"));
    }
}
