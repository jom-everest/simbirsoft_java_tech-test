package com.simbirsoft;

/**
 *
 * @author slava
 */
public interface DataLoaderService {
    abstract public Map_SL getData() throws DataLoaderException;
    
    class DataLoaderException extends Exception {
        DataLoaderException(String msg) {
            super(msg);
        }
    }
}
