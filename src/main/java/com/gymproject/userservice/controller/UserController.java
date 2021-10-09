package com.gymproject.userservice.controller;

import com.gymproject.userservice.dao.User;
import com.gymproject.userservice.dto.UserDTO;
import com.gymproject.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/api/members")
    public List<User> getAllMembers() {
        return userService.getAllMembers();
    }

    @GetMapping("/api/members/{id}")
    public User getMemberById(@PathVariable(name = "id") Long id) {
        return userService.getMemberById(id);
    }

    @GetMapping("/api/coaches")
    public List<User> getAllCoaches() {
        return userService.getAllCoaches();
    }

    @GetMapping("/api/customers")
    public List<User> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @GetMapping("/api/coaches/{id}")
    public User getCoachById(@PathVariable(name = "id") Long id) {
        return userService.getCoachById(id);
    }

    @PostMapping("/api/members/add")
    public User addMember (@RequestBody @Valid UserDTO userDTO){
        return userService.addMember(userDTO);
    }

    @PatchMapping("/api/members/update/{id}")
    public User updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDTO userDTO){
        return userService.updateUser(id, userDTO);
    }
}
