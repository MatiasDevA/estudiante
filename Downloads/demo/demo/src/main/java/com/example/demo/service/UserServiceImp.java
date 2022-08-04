
package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.model.UserModel;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserModel usermodel) {
        User user = new User();
        user.setEmail(usermodel.getEmail());
        user.setFirstName(usermodel.getFirstName());
        user.setLastName(usermodel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(usermodel.getPassword()));
        userRepository.save(user);
    return user;    
        }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
      VerificationToken verificationToken = new VerificationToken(user,token);
      
      verificationTokenRepository.save(verificationToken);
    }
    
    
    
    
    
    

   
    
}
