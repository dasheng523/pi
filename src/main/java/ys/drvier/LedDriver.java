package ys.drvier;

import com.google.inject.ImplementedBy;
import ys.component.Led;
import ys.drvier.impl.LedDriverImpl;

/**
 * Created by yesheng on 2016/9/24.
 */
@ImplementedBy(LedDriverImpl.class)
public interface LedDriver {
    //创建LED灯实例
    Led create(int channel);

    //开灯
    void light(Led led);

    //关灯
    void douse(Led led);

    //摧毁实例
    void destory(Led led);
}
