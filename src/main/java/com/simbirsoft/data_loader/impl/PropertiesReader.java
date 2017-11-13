/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.data_loader.impl;

import com.simbirsoft.data_loader.Map_SL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author slava
 */
public class PropertiesReader implements Runnable {
    
    private final Map_SL propertiesData;
    private final String fileName;
    private final StringBuffer errMessage;
    
    public PropertiesReader(Map_SL map, String fileName, StringBuffer errMessage) {
        this.propertiesData = map;
        this.fileName = fileName;
        this.errMessage = errMessage;
    }
    
    @Override
    public void run() {
        try (BufferedReader reader = (Files.exists(Paths.get(fileName))) ? 
                Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8) :
                new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName), 
                        StandardCharsets.UTF_8))){
            
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
                
                // синхронизация объекта
                synchronized(propertiesData) {
                // запись ключ + значение в массив
                    propertiesData.putIfAbsent(key, values);
                }
            }
        }
        catch (MalformedInputException e) {
            errMessage.append("Кодировка входного файла не соответствует UTF_8: ").append(fileName).append("\n");
        }
        catch (IOException e) {
            errMessage.append(e.getMessage());
        }
        catch (NullPointerException e) {
            errMessage.append("Файл не найден: ").append(fileName).append("\n");
        }
        catch (RuntimeException e) {
            errMessage.append("Some unregister error: ").append(e.getMessage());
        }
    }
    
}
