package com.gymproject.userservice.service;

import com.gymproject.userservice.dao.User;
import com.gymproject.userservice.dto.UserDTO;
import com.gymproject.userservice.enums.UserType;
import com.gymproject.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllMembers(){
        return userRepository.findAll();
    }

    public User getMemberById(Long id){
        Optional<User> storedUser = userRepository.findById(id);
        if(storedUser.isPresent()){
            return storedUser.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " not found.");
        }
    }

    public User getCoachById(Long id){
        Optional<User> storedUser = userRepository.findByIdAndUserType(id, UserType.COACH);
        if(storedUser.isPresent()){
            return storedUser.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Coach with id: " + id + " not found.");
        }
    }

    public List<User> getAllCustomers(){
        return userRepository.getByUserType(UserType.CUSTOMER);
    }

    public List<User> getAllCoaches(){
        return userRepository.getByUserType(UserType.COACH);
    }

    public User addMember(UserDTO userDTO){
        User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getUserType(), userDTO.getStatus());
        userRepository.save(user);
        return user;
    }

    public User updateUser(Long id, UserDTO userDTO){
        Optional<User> storedUser = userRepository.findById(id);

        if(storedUser.isPresent()){
            if(userDTO.getName() != null){
                storedUser.get().setName(userDTO.getName());
            }
            if(userDTO.getEmail() != null){
                storedUser.get().setEmail(userDTO.getEmail());
            }
            if(userDTO.getStatus() != null){
                storedUser.get().setStatus(userDTO.getStatus());
            }
            return userRepository.save(storedUser.get());

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " not found.");
        }
    }
}