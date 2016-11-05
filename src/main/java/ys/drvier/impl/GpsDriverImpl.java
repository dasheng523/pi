package ys.drvier.impl;

import com.pi4j.io.serial.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ys.component.Gps;
import ys.entity.PositionData;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by yesheng on 2016/9/30.
 */
public class GpsDriverImpl {
    private final Logger logger = LoggerFactory.getLogger(GpsDriverImpl.class);


    public void destroy(Gps gps) throws IOException {
        gps.getSerial().close();
        gps.setSerial(null);
    }

    public Gps create() throws InterruptedException, IOException {
        logger.info("inter");
        final Serial serial = SerialFactory.createInstance();
        Gps gps = new Gps();
        serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {
                try {
                    ByteBuffer bb = event.getByteBuffer();
                    while (true) {
                        String result = readUtil(bb, '\n');
                        if (result.equals("")) {
                            return;
                        }
                        String buffer = gps.getTempData();
                        if (buffer == null) {
                            buffer = "";
                        }
                        buffer = buffer + result;
                        if (buffer.startsWith("$GPTXT")) {
                            logger.info("空数据");
                            gps.setTempData(null);
                        }
                        else if (buffer.endsWith("\n")) {
                            logger.info("添加");
                            PositionData pd = parsePosition(buffer);
                            if (pd != null) {
                                gps.setPositionData(pd);
                            }
                        } else {
                            logger.info("替换");
                            gps.setTempData(buffer);
                        }
                    }
                } catch (Exception e) {
                    logger.error("Read GPS Data Error:", e.getStackTrace());
                }
            }
        });

        SerialConfig config = new SerialConfig();

        config.device(SerialPort.getDefaultPort())
                .baud(Baud._9600)
                .dataBits(DataBits._8)
                .parity(Parity.NONE)
                .stopBits(StopBits._1)
                .flowControl(FlowControl.NONE);

        serial.open(config);

        gps.setSerial(serial);
        return gps;
    }

    /**
     * 从ByteBuffer读数据，直到读到c为止，包含c
     * @param bb    ByteBuffer
     * @param c     终止字符
     * @return      读出字符串
     */
    private String readUtil(ByteBuffer bb, char c) {
        StringBuilder tBuffer = new StringBuilder();
        while (bb.hasRemaining()) {
            char by = (char)bb.get();
            tBuffer.append(by);
            if (by == c) {
                break;
            }
        }
        return tBuffer.toString();
    }

    private PositionData parsePosition(String input) {
        if (input == null || !input.startsWith("$GPGGA")) {
            return null;
        }
        String[] inputStrs = input.split(",");
        PositionData pd = new PositionData();
        if (inputStrs[1] != null && !inputStrs[1].equals("")) {
            pd.setTime(inputStrs[1] + inputStrs[2]);
        }
        if (inputStrs[2] != null && !inputStrs[2].equals("")
                && inputStrs[3] != null && !inputStrs[3].equals("")) {
            pd.setLat(inputStrs[2] + inputStrs[3]);
        }
        if (inputStrs[4] != null && !inputStrs[4].equals("")
                && inputStrs[5] != null && !inputStrs[5].equals("")) {
            pd.setLng(inputStrs[4] + inputStrs[5]);
        }
        if (inputStrs[9] != null && !inputStrs[9].equals("")) {
            pd.setAltitude(inputStrs[9]);
        }
        return pd;
    }

}
