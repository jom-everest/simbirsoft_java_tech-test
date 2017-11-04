package com.simbirsoft;

import com.simbirsoft.adapter.PersonDataAdapter;
import com.simbirsoft.entity.PersonInfo;
import com.simbirsoft.data_loader.DataLoaderService;
import com.simbirsoft.data_loader.impl.ParserPropertiesFile_MultiThread;
import com.simbirsoft.service.impl_htmlcreator.HtmlCreator;
import com.simbirsoft.service.ViewCreatorService;

/**
 *
 * @author slava
 * 
 */
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
            
            System.out.println(viewFileName + " was created successfully");
            
        } catch (DataLoaderService.DataLoaderException e) {
            System.err.println(e.getMessage());
        } catch (ViewCreatorService.ViewCreatorException e) {
            System.err.println(e.getMessage());
        }
    }
}
