package com.simbirsoft.data_loader;

/**
 *
 * @author slava
 */
public interface DataLoaderInterface {
    abstract public Map_SL getData() throws DataLoaderException;
    
    class DataLoaderException extends Exception {
        public DataLoaderException(String msg) {
            super(msg);
        }
    }
}
