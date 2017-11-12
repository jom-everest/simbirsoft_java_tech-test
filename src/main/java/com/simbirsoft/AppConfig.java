/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft;

import com.simbirsoft.data_loader.DataLoaderInterface;
import com.simbirsoft.data_loader.impl.LoaderPropertiesFile_MultiThread;
import com.simbirsoft.view_creator.ViewCreatorInterface;
import com.simbirsoft.view_creator.impl_htmlcreator.HtmlCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 *
 * @author slava
 */

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class AppConfig {
    
    @Value("${propertiesFile:bobunov}")
    private String subjectName;
    
    @Bean
    @Primary
    public DataLoaderInterface getLoader() {
        return new LoaderPropertiesFile_MultiThread(subjectName + "1.properties", subjectName + "2.properties");
    }
    
    @Bean 
    public ViewCreatorInterface getViewCreator() {
        return new HtmlCreator("summary_" + subjectName + ".html");
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
}
