/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft;

import com.simbirsoft.data_loader.PropertiesReader;
import com.simbirsoft.data_loader.DataLoaderService;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author slava
 */
public class DataLoaderPropertiesFile_MultiThread implements DataLoaderService {
    
    private String propertiesFileName1;
    private String propertiesFileName2;
    
    public DataLoaderPropertiesFile_MultiThread(String fn1, String fn2) {
        propertiesFileName1 = fn1;
        propertiesFileName1 = fn1;
    }
            
    @Override
    public Properties getData() throws DataLoaderException {
        Properties propertiesData = new Properties();
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable thread1 = new PropertiesReader(propertiesData, propertiesFileName1);
        Runnable thread2 = new PropertiesReader(propertiesData, propertiesFileName1);
        executor.submit(thread1);
        executor.submit(thread2);
        
        // ожидание оканчания выполнения потоков
        executor.shutdown();
    }
}
