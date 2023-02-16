package lab3.command;

import java.io.OutputStream;

public interface PrintCommand {

    void print(OutputStream os, String[] strings);

}
