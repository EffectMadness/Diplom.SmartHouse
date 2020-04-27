package org.pilipchuk.diblom.controller;

import org.pilipchuk.diblom.entities.Boiler;
import org.pilipchuk.diblom.service.BoilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlResolveController {

    @Autowired
    BoilerService boilerService;

    @GetMapping({"/", "/settings"})
    public String index(Model model) {
        Boiler boiler = boilerService.findFirst();
        model.addAttribute("boiler", boiler);
        return "settings";
    }

    @GetMapping("/chart")
    public String chart(Model model) {
        Boiler boiler = boilerService.findFirst();
        model.addAttribute("boiler", boiler);
        return "chart";
    }

}
