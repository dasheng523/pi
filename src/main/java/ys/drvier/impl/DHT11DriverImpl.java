package ys.drvier.impl;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ys.component.DHT11;
import ys.drvier.DHT11Driver;
import ys.drvier.DriverException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yesheng on 2016/11/1.
 * DHT11驱动的实现类
 */
public class DHT11DriverImpl implements DHT11Driver{
    private final Logger logger = LoggerFactory.getLogger(DHT11DriverImpl.class);
    private static final int MAXTIMINGS = 85;   // 重试次数

    @Override
    public DHT11 create(int channel) {
        if (Gpio.wiringPiSetup() == -1) {
            logger.error("初始化失败");
        }

        GpioUtil.export(channel, GpioUtil.DIRECTION_HIGH);

        DHT11 dht11 = new DHT11();
        dht11.setChannel(channel);

        return dht11;
    }

    @Override
    public Map<String, Double> getResult(DHT11 dht11) throws IOException, InterruptedException, DriverException {
        int channel = dht11.getChannel();

        for (int tryTimes = 0; tryTimes < 10; tryTimes++) {
            sendSign(channel);
            Map<String, Double> rs = acceptData(channel);
            if (rs != null) {
                return rs;
            }
        }

        // 重试多次不成功，则抛异常
        throw new DriverException("获取湿温度失败");

    }

    /**
     * 给channel发送信号
     * @param channel pin编号
     */
    private void sendSign(int channel){
        Gpio.pinMode(channel, Gpio.OUTPUT);
        Gpio.digitalWrite(channel, Gpio.LOW);
        Gpio.delay(18);
        Gpio.digitalWrite(channel, Gpio.HIGH);
        Gpio.delayMicroseconds(40);
    }

    /**
     * 接受返回数据
     * @param channel   pin编号
     * @throws IOException
     * @throws InterruptedException
     */
    private Map<String, Double> acceptData(int channel) throws IOException, InterruptedException {
        // 切换输入模式
        Gpio.pinMode(channel, Gpio.INPUT);

        // 准备接受数据
        int counter;    // 计数器
        int lastState = Gpio.HIGH;  // 设置初始状态为高电平
        int byteData[] = {0, 0, 0, 0, 0};   // 用于接收数据
        int j = 0;  // 接收数据的下标

        // 循环多次，读对应针脚的状态，模块将返回40bit数据。
        for (int i = 0; i < MAXTIMINGS; i++) {
            counter = 0;
            while (Gpio.digitalRead(channel) == lastState) {
                counter ++;
                Gpio.delayMicroseconds(1);
                if (counter >= 255) {
                    break;
                }
            }
            lastState = Gpio.digitalRead(channel);
            if (counter >= 255) {
                break;
            }

            // 忽略前4次针脚的状态变化
            if (i <= 3) {
                continue;
            }
            if (i % 2 == 0) {
                byteData[j / 8] <<= 1;
                if (counter > 16) {
                    byteData[j / 8] |= 1;
                }
                j ++;
            }
        }


        if (j >= 40 && (byteData[4] == ((byteData[0] + byteData[1] + byteData[2] + byteData[3]) & 0xff))) {
            Map<String, Double> rs = new HashMap<>();
            rs.put("humidity", Double.parseDouble(byteData[0] + "." + byteData[1]));
            rs.put("temperature", Double.parseDouble(byteData[2] + "." + byteData[3]));
            return rs;
        }

        return null;
    }

    @Override
    public void destroy(DHT11 dht11) {
        dht11.setChannel(0);
    }
}
