package imports.selecter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import imports.selecter.impl.ConsolePrinter;
import imports.selecter.impl.FilePrinter;

public class PrinterImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(PrinterSelector.class.getName()));

        Class config = attributes.getClass("value");
        return new String[]{config.getName()};
    }

    public static class ConsoleConfiguration {
        @Bean
        public ConsolePrinter consolePrint() {
            return new ConsolePrinter();
        }
    }

    public static class FileConfiguration {
        @Bean
        public FilePrinter filePrint() {
            return new FilePrinter();
        }
    }
}
