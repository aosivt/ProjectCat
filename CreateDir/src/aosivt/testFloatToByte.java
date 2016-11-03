package aosivt;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by alex on 05.11.15.
 */
public class testFloatToByte {
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[]{61, 112, -16, -15};
        int i =0;
        System.out.println("номер строки i=" + i +
                " !Наименование байтов =  " + bytes[i] + ":" + bytes[i + 1] + ":" + bytes[i + 2] + ":" + bytes[i + 3] +
                " !Полученное число = " +
                ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getFloat());
        System.out.println("номер строки i=" + i +
                " !Наименование байтов =  " + bytes[i] + ":" + bytes[i + 1] + ":" + bytes[i + 2] + ":" + bytes[i + 3] +
                " !Полученное число = " +
                ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat());

    }
}
