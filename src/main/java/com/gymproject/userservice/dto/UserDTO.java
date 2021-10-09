package com.gymproject.userservice.dto;

import com.gymproject.userservice.enums.Status;
import com.gymproject.userservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private UserType userType;

    @NotNull
    private Status status;
}