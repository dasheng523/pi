package ys.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ys.component.InfraredDetector;
import ys.drvier.impl.InfraredDetectorDriverImpl;

/**
 * Created by yesheng on 2016/9/16.
 */
public class TestInfraredDetectorDriver {
    private final Logger logger = LoggerFactory.getLogger(InfraredDetectorDriverImpl.class);

    public void testListen() throws InterruptedException {
        InfraredDetectorDriverImpl driver = new InfraredDetectorDriverImpl();
        InfraredDetector infraredDetector = driver.create(2);
        driver.listen(infraredDetector, (isHasBody) -> {
            if (isHasBody) {
                logger.info("有人来-test");
            }
            else {
                logger.info("人走了-test");
            }
        });
        Thread.sleep(60 * 60 * 1000);
    }
}
