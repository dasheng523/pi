package ys.drvier;

import com.google.inject.ImplementedBy;
import ys.component.DHT11;
import ys.drvier.impl.DHT11DriverImpl;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yesheng on 2016/11/1.
 * DHT11驱动接口
 */

@ImplementedBy(DHT11DriverImpl.class)
public interface DHT11Driver {

    /**
     * 创建DHT11实例
     * @return DHT11实例
     */
    DHT11 create(int channel);

    /**
     * 获取实例的湿度温度结果，
     * @param dht11 DHT11实例
     * @return 湿度：humidity   温度：temperature
     */
    Map<String, Double> getResult(DHT11 dht11) throws IOException, InterruptedException, DriverException;

    /**
     * 摧毁实例
     * @param dht11 DHT11实例
     */
    void destroy(DHT11 dht11);
}
