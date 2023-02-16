package lab3.command;

import java.io.OutputStream;

public class InLineCommand extends AbstractPrintCommand{

    @Override
    public void print(OutputStream os, String[] strings) {
        printWithDelimiter(os, strings, " ");
    }

}
