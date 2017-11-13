/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.data_loader.impl;

import com.simbirsoft.data_loader.DataLoaderException;
import com.simbirsoft.data_loader.DataLoaderService;
import com.simbirsoft.data_loader.Map_SL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *
 * @author slava
 */
public class ParserPropertiesFile_MultiThread implements DataLoaderService {
    
    private final static Logger LOGGER = Logger.getLogger("PropertiesFileReader");
    private final String propertiesFileName1;
    private final String propertiesFileName2;
    
    public ParserPropertiesFile_MultiThread(String fn1, String fn2) {
        propertiesFileName1 = fn1;
        propertiesFileName2 = fn2;
    }
            
    @Override
    public Map_SL getData() throws DataLoaderException {
        Map_SL propertiesData = new Map_SL();
        StringBuffer errMessage = new StringBuffer();
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable thread1 = new PropertiesReader(propertiesData, propertiesFileName1, errMessage);
        Runnable thread2 = new PropertiesReader(propertiesData, propertiesFileName2, errMessage);
        executor.submit(thread1);
        executor.submit(thread2);
        
        try {
            // ожидание окончания выполнения потоков
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } 
        catch (InterruptedException e) {
            LOGGER.warning("Execution is interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                executor.shutdownNow();
                LOGGER.warning("Threads executes very long, some problem happened obviously");
                throw new DataLoaderException("Some problem");
            }
        }
        
        if (errMessage.length() != 0) {
            throw new DataLoaderException(errMessage.toString());
        }

        return propertiesData;
    }
}
