package com.broadfactor.controller;

import com.broadfactor.dto.EmailDTO;
import com.broadfactor.model.Email;
import com.broadfactor.model.User;
import com.broadfactor.service.EmailService;
import com.broadfactor.service.UserService;
import com.broadfactor.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/email")
public class EmailController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

//    @PostMapping(path = "/forgot-password")
//    public void passwordRecover(@RequestParam("email") String email) {
//        User user = userService.findUserByEmail(email);
//        String token = UUID.randomUUID().toString();
//
//        userService.createPasswordResetTokenForUser(user, token);
//       emailService.mailSender(constructResetT0kenEmail(token, user));
//    }

    @PostMapping(path = "/send")
    public void passwordRecover(@RequestBody @Valid Email email) throws MessagingException {
        emailService.mailSender(email);
    }

}
