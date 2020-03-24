package com.pf.trading.market2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.pf.trading.market2",  "com.pf.trading.common"})
@PropertySources({
        @PropertySource("classpath:application.properties"), // common
        @PropertySource("classpath:market2.properties")
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
