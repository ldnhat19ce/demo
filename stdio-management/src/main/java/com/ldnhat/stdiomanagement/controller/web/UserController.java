package com.ldnhat.stdiomanagement.controller.web;

import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("UserWeb")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/member/list")
    public ModelAndView listUser(){
        ModelAndView modelAndView = new ModelAndView("listMember");
        modelAndView.addObject("users", userService.findAll());

        return modelAndView;
    }

    @GetMapping("/member/form")
    public String showUserForm(Model model){

        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);

        return "saveMember";
    }

    @PostMapping("/member/add")
    public String saveUser(@ModelAttribute("user")UserDto user){
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/member/form/{id}")
    public String showUserFormUpdate(@PathVariable("id") Long id, Model model){
        UserEntity userEntity = userService.findById(id);

        model.addAttribute("user", userEntity);
        return "updateMember";
    }

    @GetMapping("/member/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
