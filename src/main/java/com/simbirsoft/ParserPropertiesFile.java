package com.simbirsoft;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Парсер ".properties" файла. Извлекает данные из файла и формирует на их
 * основе ассоциативный массив Map_SL map: "ключ" = "список значений"
 *
 * @author slava
 */
public class ParserPropertiesFile implements DataLoaderService {
    
    private String fileName;
    
    final public void setFile(String fileName) {
        this.fileName = fileName;
    }
    
    public ParserPropertiesFile() {
        
    }
    public ParserPropertiesFile(String fn) {
        setFile(fn);
    }
    
    @Override
    public Map_SL getData() throws DataLoaderException {
        
        Map_SL map = new Map_SL();
        BufferedReader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8);
 //           reader = new BufferedReader(new FileReader(fileName));
            
            // подготовка рег. выражения для разбора строк файла
            Pattern pattern = Pattern.compile("\\s*\"?([^\"]+)\"?\\s*=\\s*\"?\\s*(.*?)\\s*\"?\\s*$");
            Matcher matcher;
            
           // String line = reader.readLine();
          //  while (line != null) {
            // попеременное чтение всех строк файла
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                matcher = pattern.matcher(line);
                
                // поиск совпадения строки с шаблоном рег. выражения
                if (!matcher.find()) continue; 
                
                // выделение ключа
                String key = matcher.group(1).trim().toLowerCase();

                // выделение значений
                ArrayList<String> values = new ArrayList<>(Arrays.asList(matcher.group(2).split("\"\\s*,\\s*\"")));
                values.removeIf((str) -> str.isEmpty());
                
                // запись ключ + значение в массив
                map.putIfAbsent(key, values);
            //    line = reader.readLine();
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            throw new DataLoaderException(e.getMessage());
        } 
        catch (NoSuchFileException e) {
            throw new DataLoaderException("Файл отсутствует: " + e.getMessage());
        }
        catch (MalformedInputException e) {
            throw new DataLoaderException("Кодировка входного файла не соответствует UTF_8");
        }
        catch (IOException e) {
            throw new DataLoaderException(e.getMessage());
        }
        
        return map;
    }
    
}
