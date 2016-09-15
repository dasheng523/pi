package ys.main;

import ys.component.Led;
import ys.drvier.LedDriver;

/**
 * Created by yesheng on 2016/9/12.
 */
public class Main {
    public static void main(String[] args) {
        LedDriver driver = new LedDriver();
        Led led = driver.create(1, "myLed");
        driver.light(led);
    }
}
