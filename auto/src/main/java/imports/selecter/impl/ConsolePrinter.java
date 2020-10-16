package imports.selecter.impl;

public class ConsolePrinter implements Printer {
    @Override
    public void print() {
        System.out.println("控制台输出");
    }
}