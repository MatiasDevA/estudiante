
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserModel;


public interface UserService {

    

    public User registerUser(UserModel usermodel);

    public void saveVerificationTokenForUser(String token, User user);
    
}
