package com.simbirsoft.service.impl_htmlcreator;

import com.simbirsoft.entity.PersonInfo;
import com.simbirsoft.service.ViewCreatorException;
import com.simbirsoft.service.ViewCreatorService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/** Создание html файла по персональным данным PersonInfo
 *
 * @author slava
 */

public class HtmlCreator implements ViewCreatorService {
    String fileName;
    final String SUMMARY_TEMPLATE = "summary_template.html";
    
    public final void setFileName(String fn) {
        fileName = fn;
    }
    
    public HtmlCreator(String fn) {
        setFileName(fn);
    }
    
    @Override
    public void create(PersonInfo pInfo) throws ViewCreatorException{
        try {
            String htmlStr = modifyHtml(pInfo);
            // попытка сохранить строку в файл
            saveStrToFile(htmlStr, fileName);
        }
        catch (NullPointerException ex) {
            throw new ViewCreatorException("Отсутствует файл ресурсов: " + SUMMARY_TEMPLATE);
        }
        catch (IOException ex) {
            throw new ViewCreatorException(ex.getMessage());
        }
    }
    
    // универсальная функция сохранения строки в файл
    public static void saveStrToFile(String str, String fileName) throws IOException {
        Files.write(Paths.get(fileName), str.getBytes(StandardCharsets.UTF_8));
    }
    
    // создание html элемента - список
    private String createHtmlList(List<String> list) {
        if (list.isEmpty()) return "-";
        if (list.size() == 1) return list.get(0);
        
        StringBuilder buff = new StringBuilder("<ul>");
        list.forEach((str) -> buff.append("<li>").append(str).append("</li>\n"));
        buff.append("</ul>\n");
        
        return buff.toString();
    }
    
    // создание html элемента - изображение 
    private String createHtmlImg(String imgPath) {
        StringBuilder buff = new StringBuilder();
        buff.append("<img src=\"").append(imgPath).append("\"/>");
        
        return buff.toString();
    }

    // метод формирования по html шаблону html документа, с данными из Personinfo 
    private String modifyHtml(PersonInfo pInfo) throws IOException {

        // чтение файла ресурсов SUMMARY_TEMPLATE и преобразование его с строку
        String htmlString = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(SUMMARY_TEMPLATE), StandardCharsets.UTF_8);
        
        // парсинг html шаблона
        Document html = Jsoup.parse(htmlString);
        Element elem = html.select("div.private div").first();
        
        // вставка в найденный элемент блоков с персональными данными
        elem.append(getHtml_personalBlock(pInfo.fio, "ФИО", "fio"));
        elem.append(getHtml_personalBlock(pInfo.dob, "Дата рождения", "dob"));
        elem.append(getHtml_personalBlock(pInfo.phone, "Телефон", "phone"));
        elem.append(getHtml_personalBlock(pInfo.email, "E-mail", "email"));
        elem.append(getHtml_personalBlock(pInfo.skype, "Skype", "skype"));
        
        html.select("div.avatar").html(createHtmlImg(pInfo.avatar));
        
        elem = html.select("div.prof").first();
        
        // вставка данных, связанных с профессиональной  деятельностью человека
        elem.append(getHtml_profBlock(pInfo.target, "Цель", "target"));
        elem.append(getHtml_profBlock(pInfo.experience, "Опыт работы", "experience"));
        elem.append(getHtml_profBlock(pInfo.education, "Образование", "education"));
        elem.append(getHtml_profBlock(pInfo.add_education, "Дополнительное образование и курсы", "add_education"));
        elem.append(getHtml_profBlock(pInfo.skills, "Навыки", "skills"));
        elem.append(getHtml_profBlock(pInfo.hobbies, "Хобби", "hobbies"));
        
        return html.toString();
    }
    
    private String getHtml_profBlock(List<String> list, String descr, String shortName) {
        StringBuilder buff = new StringBuilder();
        buff.append("<div class=\"").append(shortName).append("\"><h4> ").
                append(descr).append(": </h4>\n").append(createHtmlList(list)).
                append("</div>");
        
        return buff.toString();
    }
    
    private String getHtml_personalBlock(String value, String descr, String shortName) {
        StringBuilder buff = new StringBuilder();
        buff.append("<div class=\"row\">\n<div class=\"col-xs-6\" align=\"right\">\n<b>").
                append(descr).append(":").append("</b>\n</div>\n<div class=\"col-xs-6 ").
                append(shortName).append("\">").append(value.isEmpty() ? "-" : value).
                append("</div>\n</div>");
        
        return buff.toString();
    }
}
