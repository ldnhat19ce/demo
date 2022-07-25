package com.ldnhat.stdiomanagement.controller.web;

import com.ldnhat.stdiomanagement.common.constant.Constant;
import com.ldnhat.stdiomanagement.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller("skillController")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/member/{id}/skill/list")
    public ModelAndView getSkillByUserId(@PathVariable("id") Long id,
         @RequestParam(value = "pageNo", defaultValue = Constant.DEFAULT_PAGE_NUMBER, required = false)
                 int pageNo,
         @RequestParam(value = "pageSize", defaultValue = Constant.DEFAULT_PAGE_SIZE, required = false)
                     int pageSize,
         @RequestParam(value = "sortDir", defaultValue = Constant.DEFAULT_SORT_DIRECTION,
                 required = false) String sortDir){
        ModelAndView modelAndView = new ModelAndView("listSkill");
        modelAndView.addObject("skills",
                skillService.getSkillByUserId(pageNo, pageSize, sortDir, id));

        return modelAndView;
    }
}
