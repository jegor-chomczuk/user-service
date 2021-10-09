package com.gymproject.userservice.dto;

import com.gymproject.userservice.dao.User;
import com.gymproject.userservice.enums.Status;
import com.gymproject.userservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private UserType userType;

    private Status status;

    private LocalDate joinedTheGymSince;

    public UserDTO(User user) {
        setId(user.getId());
        setName(user.getName());
        setEmail(user.getEmail());
        setUserType(user.getUserType());
        setStatus(user.getStatus());
        setJoinedTheGymSince(user.getJoinedTheGymSince());
    }
}
