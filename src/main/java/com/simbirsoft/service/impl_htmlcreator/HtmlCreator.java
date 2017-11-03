package com.simbirsoft.service.impl_htmlcreator;

import com.simbirsoft.entity.PersonInfo;
import com.simbirsoft.service.ViewCreatorService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/** Создание html файла по персональным данным PersonInfo
 *
 * @author slava
 */


public class HtmlCreator implements ViewCreatorService {
    String fileName = "summary.html";
    
    // первоначальный html шаблон 
    static final String SAMPLE_HTML = "<!DOCTYPE html>\n" +
"<html lang=\"ru\">\n" +
"  <head>\n" +
"    <meta charset=\"utf-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n" +
"    <title>Bootstrap 101 Template</title>\n" +
"\n" +
"    <!-- Bootstrap -->\n" +
"	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"> \n" +
"    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n" +
"    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n" +
"    <!--[if lt IE 9]>\n" +
"      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n" +
"      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n" +
"    <![endif]-->\n" +
"    <style>\n" +
"		body {width:800px; margin:5px auto;}\n" +
"		div.private {margin-top:15px;}\n" +
"    </style>\n" +
"  </head>\n" +
"  <body>\n" +
"\n" +
"    <h3 align=\"center\">Резюме</h3>\n" +
"    <h4 align=\"center\">на должность Java-junior</h4>\n" +
"      \n" +
"    <div class=\"row private\">\n" +
"		<div class=\"col-md-8 col-xs-12\">\n" +
"		</div>\n" +
"        <div class=\"avatar col-xs-6 col-md-4\">\n" +
"            <img src=\"\"/>\n" +
"        </div>\n" +
"    </div>\n" +
"	\n" +
"	<div class=\"prof\">\n" +
"	</div>\n" +
"    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n" +
"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n" +
"    <!-- Include all compiled plugins (below), or include individual files as needed -->\n" +
"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
"  </body>\n" +
"</html>";
    
    public final void setFileName(String fn) {
        fileName = fn;
    }
    
    public HtmlCreator(String fn) {
        setFileName(fn);
    }
    
    @Override
    public void create(PersonInfo pInfo) throws ViewCreatorException{
        String htmlStr = modifyHtml(pInfo);
        try {
            // попытка сохранить строку в файл
            saveStrToFile(htmlStr, fileName);
        }
        catch (IOException e) {
            throw new ViewCreatorException(e.getMessage());
        }
    }
    
    // универсальная функция сохранения строки в файл
    public static void saveStrToFile(String str, String fileName) throws IOException {
        Files.write(Paths.get(fileName), str.getBytes(StandardCharsets.UTF_8));
    }
    
    // создание html элемента - список
    private String createHtmlList(ArrayList<String> list) {
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
    private String modifyHtml(PersonInfo pInfo) {

        Document html = Jsoup.parse(SAMPLE_HTML);
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
    
    private String getHtml_profBlock(ArrayList<String> list, String descr, String shortName) {
        StringBuilder buff = new StringBuilder();
        buff.append("<div class=\"").append(shortName).append("\"><h4> ").
                append(descr).append(": </h4>\n").append(createHtmlList(list)).
                append("</div>");
        
        return buff.toString();
    }
    
    private String getHtml_personalBlock(String value, String descr, String shortName) {
/*        elem.appendElement("div").addClass("row").child(0).
                appendElement("div").addClass("col-xs-6").attr("align", "right").child(0).
                appendElement("b").text(descr + ":");
*/
        StringBuilder buff = new StringBuilder();
        buff.append("<div class=\"row\">\n<div class=\"col-xs-6\" align=\"right\">\n<b>").
                append(descr).append(":").append("</b>\n</div>\n<div class=\"col-xs-6 ").
                append(shortName).append("\">").append(value.isEmpty() ? "-" : value).
                append("</div>\n</div>");
        
        return buff.toString();
    }
}
