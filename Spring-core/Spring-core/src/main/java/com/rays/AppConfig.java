package com.rays;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //read the @component and @value annotations and configure them
@ComponentScan("com.rays")//it scans all the class inside this package
public class AppConfig {
}