package com.ldnhat.stdiomanagement.controller;

import com.ldnhat.stdiomanagement.dto.SkillDto;
import com.ldnhat.stdiomanagement.response.SkillResponse;
import com.ldnhat.stdiomanagement.service.SkillService;
import com.ldnhat.stdiomanagement.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/{id}/skills")
    public SkillResponse getSkillByUserId(
            @RequestParam(value = "pageNo", defaultValue = Constant.DEFAULT_PAGE_NUMBER, required = false)
                    int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constant.DEFAULT_PAGE_SIZE, required = false)
                    int pageSize,
            @PathVariable("id") Long id){
        return skillService.getSkillByUserId(pageNo, pageSize, id);
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<SkillDto> saveUser(@RequestBody SkillDto skillDto,
                                            @PathVariable("id") Long id){
        return new ResponseEntity<>(skillService.save(skillDto, id), HttpStatus.CREATED);
    }

}
