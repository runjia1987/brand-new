package org.jackJew.brand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/swagger-ui")
public class SwaggerController {

    @RequestMapping(method = RequestMethod.GET)
    public String display() {
        return "redirect:swagger-ui.html";
    }
}

