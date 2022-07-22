package com.ldnhat.stdiomanagement.controller.api;

import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ApiProjectController")
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProjectDto>> getAllProjects(){
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDto> saveProject(@RequestBody ProjectDto projectDto){
        return new ResponseEntity<>(projectService.save(projectDto), HttpStatus.CREATED);
    }
}
