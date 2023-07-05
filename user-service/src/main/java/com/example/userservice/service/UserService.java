package com.example.userservice.service;

import com.example.userservice.constants.RentalRight;
import com.example.userservice.constants.UserConstants;
import com.example.userservice.entity.User;
import com.example.userservice.historyApi.HistoryApi;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.response.RentalRightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HistoryApi historyApi;

    public void registration(String email, String password){
        User newUser=User.of(email, password);
        userRepository.save(newUser);
    }

    public RentalRightResponse checkRentalRight(String email){
        User user=findUserByUserEmail(email);
        int bookQuantity=historyApi.getRentalBookQuantity(user.getId());

        if(isAvailableRental(bookQuantity)){
            return RentalRightResponse.of(RentalRight.RENTAL_AVAILABLE);
        }else{
            return RentalRightResponse.of(RentalRight.RENTAL_NOT_AVAILABLE);
        }
    }

    private boolean isAvailableRental(int quantity){
        if(quantity<UserConstants.LIMITED_RENTAL_BOOK_COUNT){
            return true;
        }
        return false;
    }

    private User findUserByUserEmail(String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException());

        return user;
    }

}
