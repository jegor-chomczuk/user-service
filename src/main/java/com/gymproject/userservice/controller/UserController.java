package com.gymproject.userservice.controller;

import com.gymproject.userservice.dto.UserCreatorDTO;
import com.gymproject.userservice.dto.UserDTO;
import com.gymproject.userservice.enums.UserType;
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
    public List<UserDTO> getAllMembers() {
        return userService.getAllMembers();
    }

    @GetMapping("/api/members/{id}")
    public UserDTO getMemberById(@PathVariable(name = "id") Long id) {
        return userService.getMemberById(id);
    }

    @GetMapping("/api/coaches")
    public List<UserDTO> getAllCoaches() {
        return userService.getAllCoaches();
    }

    @GetMapping("/api/coaches/{id}")
    public UserDTO getCoachById(@PathVariable(name = "id") Long id) {
        return userService.getMemberByIdAndUserType(id, UserType.COACH);
    }

    @GetMapping("/api/coaches/active")
    public List<UserDTO> getAllActiveCoaches() {
        return userService.getAllActiveCoaches();
    }

    @GetMapping("/api/customers")
    public List<UserDTO> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @GetMapping("/api/customers/{id}")
    public UserDTO getCustomerById(@PathVariable(name = "id") Long id) {
        return userService.getMemberByIdAndUserType(id, UserType.CUSTOMER);
    }

    @PostMapping("/api/members/add")
    public UserDTO addMember(@RequestBody @Valid UserCreatorDTO userCreatorDTO) {
        return userService.addMember(userCreatorDTO);
    }

    @PatchMapping("/api/members/update/{id}")
    public UserDTO updateUser(@PathVariable(name = "id") Long id, @RequestBody UserCreatorDTO userCreatorDTO) {
        return userService.updateUser(id, userCreatorDTO);
    }
}
