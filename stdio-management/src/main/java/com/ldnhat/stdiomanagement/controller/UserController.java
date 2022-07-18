package com.ldnhat.stdiomanagement.controller;

import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    public ResponseEntity<List<UserEntity>> getAllUsers() throws ParseException {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity){
        return new ResponseEntity<>(userService.save(userEntity), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> editUser(@RequestBody UserEntity userEntity,
                                               @PathVariable("id") Long id){
        UserEntity userResponse = userService.edit(userEntity, id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("user deleted successfully", HttpStatus.OK);
    }
}
