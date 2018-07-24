package com.lunarshade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

    @RequestMapping(value = "/main-page")

    public String getMainPage(Model model) {

        return "mainPage";

    }

}
