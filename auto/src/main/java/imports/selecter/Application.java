package imports.selecter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import imports.selecter.impl.Printer;

@PrinterSelector(PrinterImportSelector.FileConfiguration .class)
//@PrinterSelector
@SpringBootApplication
public class Application {

    @Autowired
    public Application(Printer printer) {
        printer.print();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}

