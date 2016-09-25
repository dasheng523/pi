package ys.drvier;

import com.google.inject.ImplementedBy;
import ys.component.InfraredDetector;
import ys.drvier.impl.InfraredDetectorDriverImpl;

import java.util.function.Consumer;

/**
 * Created by yesheng on 2016/9/24.
 */
@ImplementedBy(InfraredDetectorDriverImpl.class)
public interface InfraredDetectorDriver {

    // 创建实例
    InfraredDetector create(int dataChannel);

    // 摧毁实例
    void destroy(InfraredDetector infraredDetector);

    // 监听是否有人到来
    void listen(InfraredDetector infraredDetector, final Consumer<Boolean> bodyNearHandler);
}
