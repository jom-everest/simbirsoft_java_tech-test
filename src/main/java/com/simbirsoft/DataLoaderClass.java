package summaryhtmlcreator;

/**
 *
 * @author slava
 */
abstract public class DataLoaderClass {
    abstract public Map_SL getData() throws DataLoaderException;
    
    class DataLoaderException extends Exception {
        DataLoaderException(String msg) {
            super(msg);
        }
    }
}
