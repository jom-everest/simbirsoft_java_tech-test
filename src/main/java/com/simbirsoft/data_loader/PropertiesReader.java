/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.data_loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author slava
 */
public class PropertiesReader implements Runnable {
    
    private final Map_SL propertiesData;
    private final String fileName;
    
    public PropertiesReader(Map_SL map, String fileName) {
        this.propertiesData = map;
        this.fileName = fileName;
    }
    
    @Override
    public void run() {
//            propertiesData.load(new InputStreamReader(getClass().getClassLoader().
//                    getResourceAsStream(fileName), StandardCharsets.UTF_8));

        Object lock;
        try (BufferedReader reader = (Files.exists(Paths.get(fileName))) ? 
                Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8) :
                new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName), 
                        StandardCharsets.UTF_8))) {
            
            // подготовка рег. выражения для разбора строк файла
            Pattern pattern = Pattern.compile("\\s*\"?([^\"]+)\"?\\s*=\\s*\"?\\s*(.*?)\\s*\"?\\s*$");
            Matcher matcher;
            
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
                sinchronized(lock) {
                    propertiesData.putIfAbsent(key, values);
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new DataLoaderService.DataLoaderException(e.getMessage());
        } 
        catch (NoSuchFileException e) {
            throw new DataLoaderService.DataLoaderException("Файл отсутствует: " + e.getMessage());
        }
        catch (MalformedInputException e) {
            throw new DataLoaderService.DataLoaderException("Кодировка входного файла не соответствует UTF_8");
        }
        catch (IOException e) {
            throw new DataLoaderService.DataLoaderException(e.getMessage());
        }

    }
    
}
