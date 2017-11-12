package com.simbirsoft;

import com.simbirsoft.service.HtmlGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author slava
 * 
 */

@SpringBootApplication
public class Application {

    /**
     * @param args аргумент 
     */
    public static void main(String[] args) {

        ConfigurableApplicationContext context = 
                SpringApplication.run(new Class<?>[] {Application.class, AppConfig.class}, args);
        
        HtmlGenerator gen = context.getBean(HtmlGenerator.class);
        gen.createHtml();
    }
}
