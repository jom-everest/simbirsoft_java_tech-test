package com.simbirsoft;

import com.simbirsoft.adapter.PersonDataAdapter;
import com.simbirsoft.data_loader.DataLoaderException;
import com.simbirsoft.entity.PersonInfo;
import com.simbirsoft.data_loader.DataLoaderService;
import com.simbirsoft.data_loader.impl.ParserPropertiesFile_MultiThread;
import com.simbirsoft.service.ViewCreatorException;
import com.simbirsoft.service.impl_htmlcreator.HtmlCreator;
import com.simbirsoft.service.ViewCreatorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slava
 * 
 */
public class Application {

    /**
     * @param args аргумент 
     */
    
    private final static Logger LOGGER = Logger.getLogger("MainApp");
    
    public static void main(String[] args) {

        String subjectName = "bobunov";
        try {
            if ((args.length != 0) && (!args[0].isEmpty())) {
                subjectName = args[0];
            }
            
            // creating parser-object, that will be parse ".properties" file on keys and values 
            DataLoaderService parser = new ParserPropertiesFile_MultiThread(
                    subjectName + "1.properties", subjectName + "2.properties");
            
            // creating adapter-object, that will be extract only necessary "person" 
            // data with "parser"
            PersonDataAdapter adapter = new PersonDataAdapter(parser);
            PersonInfo pInfo = adapter.getData();
            
            // phase of creating summary html file on data of "properties" file
            String viewFileName = "summary_" + subjectName + ".html";
            ViewCreatorService viewCreator = new HtmlCreator(viewFileName);
            viewCreator.create(pInfo);
            
            LOGGER.log(Level.INFO, "{0} was created successfully", viewFileName);
            
        } catch (DataLoaderException e) {
            LOGGER.warning(e.getMessage());
        } catch (ViewCreatorException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
