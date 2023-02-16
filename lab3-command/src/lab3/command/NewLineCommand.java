package lab3.command;

import java.io.OutputStream;

public class NewLineCommand extends AbstractPrintCommand{

    public void print(OutputStream os, String[] strings) {
        printWithDelimiter(os, strings, "\n");
    }

}
