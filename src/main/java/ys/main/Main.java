package ys.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yesheng on 2016/9/12.
 */
public class Main {
    private final Logger logger = LoggerFactory.getLogger(Main.class);

    public void log() {
        logger.error("ddddd");
    }

    public static void main(String[] args) {
        new Main().log();
    }
}
