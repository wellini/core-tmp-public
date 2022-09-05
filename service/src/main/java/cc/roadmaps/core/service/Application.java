package cc.roadmaps.core.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    static {
        io.swagger.v3.core.jackson.ModelResolver.enumsAsRef = true;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
