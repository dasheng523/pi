package ys.drvier;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Created by yesheng on 2016/9/12.
 */
public class PinFactory {
    public static Pin getPin(int channel) {
        switch (channel) {
            case 0 : return RaspiPin.GPIO_00;
            case 1 : return RaspiPin.GPIO_01;
            case 2 : return RaspiPin.GPIO_02;
            case 3 : return RaspiPin.GPIO_03;
            case 4 : return RaspiPin.GPIO_04;
            case 5 : return RaspiPin.GPIO_05;
            case 6 : return RaspiPin.GPIO_06;
            case 7 : return RaspiPin.GPIO_07;
            case 8 : return RaspiPin.GPIO_08;
            case 9 : return RaspiPin.GPIO_09;
            case 10 : return RaspiPin.GPIO_10;
            case 11 : return RaspiPin.GPIO_11;
            case 12 : return RaspiPin.GPIO_12;
            case 13 : return RaspiPin.GPIO_13;
            case 14 : return RaspiPin.GPIO_14;
            case 15 : return RaspiPin.GPIO_15;
            case 16 : return RaspiPin.GPIO_16;
            case 17 : return RaspiPin.GPIO_17;
            case 18 : return RaspiPin.GPIO_18;
            case 19 : return RaspiPin.GPIO_19;
            case 20 : return RaspiPin.GPIO_20;
            case 21 : return RaspiPin.GPIO_21;
            case 22 : return RaspiPin.GPIO_22;
            case 23 : return RaspiPin.GPIO_23;
            case 24 : return RaspiPin.GPIO_24;
            case 25 : return RaspiPin.GPIO_25;
            case 26 : return RaspiPin.GPIO_26;
            case 27 : return RaspiPin.GPIO_27;
            case 28 : return RaspiPin.GPIO_28;
            case 29 : return RaspiPin.GPIO_29;
            case 30 : return RaspiPin.GPIO_30;
            case 31 : return RaspiPin.GPIO_31;
            default: return RaspiPin.GPIO_01;
        }
    }
}
