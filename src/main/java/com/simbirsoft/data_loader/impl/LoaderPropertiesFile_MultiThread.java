/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.data_loader.impl;

import com.simbirsoft.data_loader.Map_SL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.simbirsoft.data_loader.DataLoaderInterface;

/**
 *
 * @author slava
 */

public class LoaderPropertiesFile_MultiThread implements DataLoaderInterface {
    
    private static final int SECONDS_WAIT = 10;
    private final String propertiesFileName1;
    private final String propertiesFileName2;
    
    public LoaderPropertiesFile_MultiThread(String fn1, String fn2) {
        propertiesFileName1 = fn1;
        propertiesFileName2 = fn2;
    }
            
    @Override
    public Map_SL getData() throws DataLoaderException {
        Map_SL propertiesData = new Map_SL();
        StringBuffer errMessage = new StringBuffer();
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable thread1 = new LoaderPropertiesFile_thread(propertiesData, propertiesFileName1, errMessage);
        Runnable thread2 = new LoaderPropertiesFile_thread(propertiesData, propertiesFileName2, errMessage);
        executor.submit(thread1);
        executor.submit(thread2);
        
        try {
            // ожидание оканчания выполнения потоков
            executor.shutdown();
            executor.awaitTermination(SECONDS_WAIT, TimeUnit.SECONDS);
        } 
        catch (InterruptedException e) {
        }
        finally {
            if (!executor.isTerminated()) {
                executor.shutdownNow();
                System.err.println("Threads executes very long, some problem happened obviously");
                throw new DataLoaderException("Проблема с потоками");
            }
        }
        
        if (errMessage.length() != 0) {
            throw new DataLoaderException(errMessage.toString());
        }

        return propertiesData;
    }
}
