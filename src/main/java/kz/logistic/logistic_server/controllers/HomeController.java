package kz.logistic.logistic_server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Assylkhan
 * on 19.05.2019
 * @project logistic_server
 */
@Controller
public class HomeController {

    @RequestMapping("")
    public String index(){
        return  "index";
    }
}
