package com.ldnhat.stdiomanagement.controller.web;

import com.ldnhat.stdiomanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller("projectController")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project/list")
    public ModelAndView listProject(){
        ModelAndView modelAndView = new ModelAndView("listProject");
        modelAndView.addObject("projects", projectService.findAll());

        return modelAndView;
    }


}
