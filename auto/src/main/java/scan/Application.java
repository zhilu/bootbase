package scan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MetaComponentScan
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}

