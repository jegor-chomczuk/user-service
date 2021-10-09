package com.gymproject.userservice.service;

import com.gymproject.userservice.dao.User;
import com.gymproject.userservice.dto.UserCreatorDTO;
import com.gymproject.userservice.dto.UserDTO;
import com.gymproject.userservice.enums.Status;
import com.gymproject.userservice.enums.UserType;
import com.gymproject.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserDTO> getAllMembers() {
        List<User> userList = userRepository.findAll();

        return userListToUserDTOList(userList);
    }

    public UserDTO getMemberById(Long id) {
        Optional<User> storedUser = userRepository.findById(id);
        if (storedUser.isPresent()) {
            return new UserDTO(storedUser.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " not found.");
        }
    }

    public UserDTO getMemberByIdAndUserType(Long id, UserType userType) {
        Optional<User> storedUser = userRepository.findByIdAndUserType(id, userType);
        if (storedUser.isPresent()) {
            return new UserDTO(storedUser.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with id: " + id + " of a user type: " + userType + " not found.");
        }
    }

    public List<UserDTO> getAllCustomers() {
        return userListToUserDTOList(userRepository.getByUserType(UserType.CUSTOMER));
    }

    public List<UserDTO> getAllCoaches() {
        return userListToUserDTOList(userRepository.getByUserType(UserType.COACH));
    }

    public UserDTO addMember(UserCreatorDTO userCreatorDTO) {
        User user = new User(userCreatorDTO.getName(), userCreatorDTO.getEmail(), userCreatorDTO.getUserType(), userCreatorDTO.getStatus());
        userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO updateUser(Long id, UserCreatorDTO userCreatorDTO) {
        Optional<User> storedUser = userRepository.findById(id);

        if (storedUser.isPresent()) {
            if (userCreatorDTO.getName() != null) {
                storedUser.get().setName(userCreatorDTO.getName());
            }
            if (userCreatorDTO.getEmail() != null) {
                storedUser.get().setEmail(userCreatorDTO.getEmail());
            }
            if (userCreatorDTO.getStatus() != null) {
                storedUser.get().setStatus(userCreatorDTO.getStatus());
            }
            return new UserDTO(userRepository.save(storedUser.get()));

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " not found.");
        }
    }

    public List<UserDTO> getAllActiveCoaches() {
        return userListToUserDTOList(userRepository.getByUserTypeAndStatus(UserType.COACH, Status.ACTIVE));
    }

    public List<UserDTO> userListToUserDTOList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User a : userList) {
            userDTOList.add(new UserDTO(a));
        }

        return userDTOList;
    }
}