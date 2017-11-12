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
     * 1. может содержать имя ".properties" файла, содержащие данные о соискателе 
     * в формате "ключ" = "значение", и из данных, которых будет сформирован
     * "summary html" файл; в этом случае имя файла указывается без расширения 
     * ".properties", например просто "gavrilov", а в дальнейшем программа сама 
     * самостоятельно будет искать в текущем каталоге (в том откуда она была вызвана)
     * полное имя файла "gavrilov.properties"
     * 2. может быть не указан, в таком случае ".properties" файл по умолчанию 
     * будет принят файл "bobunov.properties".
     * 
     * Замечания: "properties" файл будет искаться в том же каталоге из которого 
     * вызывается программа
     */
    public static void main(String[] args) {

        ConfigurableApplicationContext context = 
                SpringApplication.run(new Class<?>[] {Application.class, AppConfig.class}, args);
        
        HtmlGenerator gen = context.getBean(HtmlGenerator.class);
        gen.createHtml();
    }
}
