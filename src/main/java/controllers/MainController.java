package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    //при старте проекта загружает index.jsp
    @RequestMapping(value = "/")
    public String main (){
        return "index";
    }
}
