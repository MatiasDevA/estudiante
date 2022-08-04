
package com.example.demo.controller;

import com.example.demo.event.RegistrationCompleteEvent;
import com.example.demo.model.User;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

 @RestController
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
        return "http://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath();
    }
    
}
