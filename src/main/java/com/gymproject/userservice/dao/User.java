package com.gymproject.userservice.dao;

import com.gymproject.userservice.enums.Status;
import com.gymproject.userservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private UserType userType;

    private Status status;

    private LocalDate joinedTheGymSince;

    public User(String name, String email, UserType userType, Status status) {
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.status = status;
        setJoinedTheGymSince(LocalDate.now());
    }
}
