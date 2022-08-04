
package com.example.demo.controller;

import com.example.demo.event.RegistrationCompleteEvent;
import com.example.demo.model.PasswordModel;
import com.example.demo.model.User;
import com.example.demo.model.UserModel;
import com.example.demo.model.VerificationToken;
import com.example.demo.service.UserService;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 @RestController
 @Slf4j
public class RegistrationController {
     
     
     @Autowired
     private UserService userService;
     
     @Autowired
     private ApplicationEventPublisher publisher;
     
     @PostMapping("/register")
        public String registerUser(@RequestBody UserModel usermodel, final HttpServletRequest request){
            User user = userService.registerUser(usermodel);
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
            return "Success";
        }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + "/"+ request.getContextPath();
    }
    
    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verifies Successfully";
        }
        return "Bad User";
    }
    
    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token")String oldToken, 
            HttpServletRequest request){
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        
        resendVerificationTokenMail(user,applicationUrl(request), verificationToken);
        return "verification Link SENT";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
      String url = applicationUrl + "verifyRegistration?token=" + verificationToken.getToken();
        
        //sebfVerificationEmail()
        log.info("click the link to verify your account: {}", url );
    }
    
    @PostMapping("/resetPassword")
    public  String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        
        if(user!=null){
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user,token);
            url = passwordResetTokenMail(user, applicationUrl(request),token);
        }
        return url;
    }
    @PostMapping("/savePassword")
     public String savePassword(@RequestParam("token")String token, @RequestBody PasswordModel passwordModel){
         String result = userService.validatePasswordResetToken(token);
         
         if(!result.equalsIgnoreCase("valid")){
             return "Invalid Token";
         }
       Optional<User> user = userService.getUserByPasswordResetToken(token);
       
       if(user.isPresent()){
           userService.changePassword(user.get(), passwordModel.getNewPassword());
           return "Password Reset Succesfull";
       } else {
           return "Invalid Token";
       }
     }
     @PostMapping("/changePassword")
     public String changePassword(@RequestBody PasswordModel passwordModel){
         User user = userService.findUserByEmail(passwordModel.getEmail());
         
         if(!userService.checkIfValidOldPassword(user,passwordModel.getOldPassword())){
             return "Invalid Old Password";
         }
         
         //save New Password
         userService.changePassword(user, passwordModel.getNewPassword());
         return "password changed sucessfully";
     }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
      String url = applicationUrl + "/savePassword?token=" + token;
        
        //sebfVerificationEmail()
        log.info("Click the link to Reset your Password: {}", url );
        
        return url;
    }
    
    
}
