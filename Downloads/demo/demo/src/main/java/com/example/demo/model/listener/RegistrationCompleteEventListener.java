
package com.example.demo.model.listener;

import com.example.demo.event.RegistrationCompleteEvent;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;
    
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create the verification token for the user with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);
        
        //send mail to user
        String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;
        
        //sebfVerificationEmail()
        log.info("click the link to verify your account: {}", url );
    }
    
}
