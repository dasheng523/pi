package ys.driver;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ys.component.DHT11;
import ys.drvier.DHT11Driver;
import ys.drvier.impl.DHT11DriverImpl;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yesheng on 2016/11/1.
 * 测试DHT11用例
 */
public class TestDHT11Driver {
    private final Logger logger = LoggerFactory.getLogger(TestDHT11Driver.class);

    @Test
    public void testtest() throws IOException, InterruptedException {
        DHT11Driver driver = new DHT11DriverImpl();
        DHT11 dht11 = driver.create(0);
        for (int i = 0; i < 5; i++) {
            Map<String, Double> rs = driver.getResult(dht11);
            if (rs != null) {
                logger.info(rs.toString());
            }
            Thread.sleep(2000);
        }


    }
}
