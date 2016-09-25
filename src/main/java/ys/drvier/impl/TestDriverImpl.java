package ys.drvier.impl;

import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ys.drvier.TestDriver;

/**
 * Created by yesheng on 2016/9/24.
 */

@Singleton
public class TestDriverImpl implements TestDriver {
    private Logger logger = LoggerFactory.getLogger(TestDriverImpl.class);

    @Override
    public boolean test() {
        logger.info("test driver");
        return true;
    }
}
