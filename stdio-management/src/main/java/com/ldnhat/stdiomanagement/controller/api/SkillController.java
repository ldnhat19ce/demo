package com.ldnhat.stdiomanagement.controller.api;

import com.ldnhat.stdiomanagement.dto.SkillDto;
import com.ldnhat.stdiomanagement.payload.Response;
import com.ldnhat.stdiomanagement.service.SkillService;
import com.ldnhat.stdiomanagement.common.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("ApiSkillController")
@RequestMapping("/api/member")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/{id}/skills")
    public Response<SkillDto> getSkillByUserId(
            @RequestParam(value = "pageNo", defaultValue = Constant.DEFAULT_PAGE_NUMBER, required = false)
                    int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constant.DEFAULT_PAGE_SIZE, required = false)
                    int pageSize,
            @RequestParam(value = "sortDir", defaultValue = Constant.DEFAULT_SORT_DIRECTION,
                    required = false) String sortDir,
            @PathVariable("id") Long id){
        return skillService.getSkillByUserId(pageNo, pageSize, sortDir, id);
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<SkillDto> saveSkillOfUser(@RequestBody SkillDto skillDto,
                                            @PathVariable("id") Long id){
        return new ResponseEntity<>(skillService.save(skillDto, id), HttpStatus.CREATED);
    }

    @PostMapping("/skills")
    public ResponseEntity<SkillDto> saveSkill(@RequestBody SkillDto skillDto){
        return new ResponseEntity<>(skillService.save(skillDto), HttpStatus.CREATED);
    }

}
