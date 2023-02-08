package lab2.adapter;

import java.io.IOException;
import java.io.OutputStream;

public class StringOutputStream {

    private OutputStream os;

    public StringOutputStream(OutputStream os) {
        this.os = os;
    }

    public void write(String[] strs) throws IOException {
        for(String str : strs) {
            os.write(str.getBytes());
        }
    }
}
