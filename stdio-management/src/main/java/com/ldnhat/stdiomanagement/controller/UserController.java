package com.ldnhat.stdiomanagement.controller;

import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.response.SkillResponse;
import com.ldnhat.stdiomanagement.service.UserService;
import com.ldnhat.stdiomanagement.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto,
                                               @PathVariable("id") Long id){
        UserDto userResponse = userService.edit(userDto, id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("user deleted successfully", HttpStatus.OK);
    }
}
