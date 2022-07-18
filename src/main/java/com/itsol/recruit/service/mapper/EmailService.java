package com.itsol.recruit.service.mapper;

// Importing required classes


// Interface
public interface EmailService {

    // Method
    // To send a simple email
    boolean sendEmail(String to ,String email);

    String buildActiveLink(String url);

    // Method
    // To send an email with attachment
}
