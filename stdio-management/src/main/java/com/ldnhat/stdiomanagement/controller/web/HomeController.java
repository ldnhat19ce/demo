package com.ldnhat.stdiomanagement.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){

        return "redirect:/member/list";
    }
}
