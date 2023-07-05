package com.example.userservice.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSignUpDto {
    private String email;
    private String password;
}
