package ys.drvier;

import com.google.inject.ImplementedBy;
import ys.drvier.impl.TestDriverImpl;

/**
 * Created by yesheng on 2016/9/24.
 */

@ImplementedBy(TestDriverImpl.class)
public interface TestDriver {
    boolean test();
}
