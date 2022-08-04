
package com.example.demo.service;

import com.example.demo.model.PasswordModel;
import com.example.demo.model.User;
import com.example.demo.model.UserModel;
import com.example.demo.model.VerificationToken;
import java.util.Optional;


public interface UserService {

    

    public User registerUser(UserModel usermodel);

    public void saveVerificationTokenForUser(String token, User user);

    public String validateVerificationToken(String token);

    public VerificationToken generateNewVerificationToken(String oldToken);

    public User findUserByEmail(String email);

    public void createPasswordResetTokenForUser(User user, String token);

    public String validatePasswordResetToken(String token);

    public Optional<User> getUserByPasswordResetToken(String token);

    public void changePassword(User user, String newPassword);

    public boolean checkIfValidOldPassword(User user, String oldPassword);
    
}
