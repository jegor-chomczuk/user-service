package com.gymproject.userservice.controller;

import com.gymproject.userservice.dto.UserCreatorDTO;
import com.gymproject.userservice.dto.UserDTO;
import com.gymproject.userservice.enums.UserType;
import com.gymproject.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/api/members")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllMembers() {
        return userService.getAllMembers();
    }

    @GetMapping("/api/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getMemberById(@PathVariable(name = "id") Long id) {
        return userService.getMemberById(id);
    }

    @GetMapping("/api/coaches")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllCoaches() {
        return userService.getAllCoaches();
    }

    @GetMapping("/api/coaches/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getCoachById(@PathVariable(name = "id") Long id) {
        return userService.getMemberByIdAndUserType(id, UserType.COACH);
    }

    @GetMapping("/api/coaches/active")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllActiveCoaches() {
        return userService.getAllActiveCoaches();
    }

    @GetMapping("/api/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @GetMapping("/api/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getCustomerById(@PathVariable(name = "id") Long id) {
        return userService.getMemberByIdAndUserType(id, UserType.CUSTOMER);
    }

    @PostMapping("/api/members/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addMember(@RequestBody @Valid UserCreatorDTO userCreatorDTO) {
        return userService.addMember(userCreatorDTO);
    }

    @PatchMapping("/api/members/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserDTO updateUser(@PathVariable(name = "id") Long id, @RequestBody UserCreatorDTO userCreatorDTO) {
        return userService.updateUser(id, userCreatorDTO);
    }
}
