package com.broadfactor.service;

import com.broadfactor.model.Email;

import javax.mail.MessagingException;

public interface EmailService {

    void mailSender(Email email) throws MessagingException;
}
