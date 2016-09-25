package ys.drvier;

import com.google.inject.ImplementedBy;
import ys.component.Ultrasound;
import ys.drvier.impl.UltrasoundDriverImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by yesheng on 2016/9/25.
 */
@ImplementedBy(UltrasoundDriverImpl.class)
public interface UltrasoundDriver {

    // 创建超声波模块实例
    Ultrasound create(int trigChannel, int echoChannel);

    // 摧毁模块实例
    void destroy(Ultrasound ultrasound);

    /**
     * 测量距离
     * @param ultrasound    超声波实例
     */
    double measureDistance(Ultrasound ultrasound) throws InterruptedException, ExecutionException, TimeoutException;
}
