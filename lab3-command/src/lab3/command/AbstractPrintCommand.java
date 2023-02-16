package lab3.command;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public abstract class AbstractPrintCommand implements PrintCommand {

    protected void printWithDelimiter(OutputStream os, String[] strings, String delimiter) {
        OutputStreamWriter osw = new OutputStreamWriter(os);
        try {
            for(String str : strings) {
                osw.write(str);
                osw.write(delimiter);
            }
            osw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
