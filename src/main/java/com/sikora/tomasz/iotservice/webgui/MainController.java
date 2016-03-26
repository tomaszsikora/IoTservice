package com.sikora.tomasz.iotservice.webgui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tomasz Sikora for personal use.
 */
@Controller
public class MainController
{

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}