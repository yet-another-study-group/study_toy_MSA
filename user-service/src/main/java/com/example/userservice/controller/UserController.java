package com.example.userservice.controller;

import com.example.userservice.response.ApiResponse;
import com.example.userservice.response.RentalRightResponse;
import com.example.userservice.response.UserSignUpDto;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/rentalRight/{userEmail}")
    public ResponseEntity<RentalRightResponse> checkRentalRight(@PathVariable String userEmail){
        RentalRightResponse response=userService.checkRentalRight(userEmail);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/registration")
    public ResponseEntity registrationUser(@Validated @RequestBody UserSignUpDto userSignUpDto){
        userService.registration(userSignUpDto.getEmail(), userSignUpDto.getPassword());
        return ResponseEntity.ok("회원가입 성공");
    }

}
