package com.example.ExploreWithMeMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ExploreWithMeMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExploreWithMeMainApplication.class, args);
        System.out.println(LocalDate.now());
    }

}
