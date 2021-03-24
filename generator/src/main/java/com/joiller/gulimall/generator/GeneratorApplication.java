package com.joiller.gulimall.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:generator.properties")
public class GeneratorApplication {

    @Value("${parent}")
    private static String parent;

    public static void main(String[] args) {
        System.out.println("hello++++++++++++++");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(parent);

        SpringApplication.run(GeneratorApplication.class, args);
    }

}
