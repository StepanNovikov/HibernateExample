package hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Stepan Novikov
 */

@SpringBootApplication(scanBasePackages = "hibernate")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
