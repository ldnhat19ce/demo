package com.ldnhat.stdiomanagement.controller.api;

import com.ldnhat.stdiomanagement.common.constant.Constant;
import com.ldnhat.stdiomanagement.dto.ProjectDetailDto;
import com.ldnhat.stdiomanagement.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectDetailController {

    private final ProjectDetailService projectDetailService;

    @Autowired
    public ProjectDetailController(ProjectDetailService projectDetailService) {
        this.projectDetailService = projectDetailService;
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<ProjectDetailDto> saveProjectDetail(@RequestBody ProjectDetailDto projectDetailDto,
                                                              @PathVariable("id") Long id){
        return new ResponseEntity<>(projectDetailService.save(projectDetailDto, id), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/edit/{projectDetailId}")
    public ResponseEntity<ProjectDetailDto> editProjectDetail(@RequestBody ProjectDetailDto projectDetailDto,
                                                              @PathVariable("projectDetailId") Long projectDetailId){
        return new ResponseEntity<>(projectDetailService
                .edit(projectDetailDto, projectDetailId), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}/delete/{id}")
    public ResponseEntity<String> deleteProjectDetail(@PathVariable("id") Long id){
        projectDetailService.deleteById(id);
        return new ResponseEntity<>(Constant.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }
}
