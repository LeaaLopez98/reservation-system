package app.reservationsystem.emails.service;

import app.reservationsystem.emails.builder.EmailBuilder;

public interface EmailService {
    void sendEmail(EmailBuilder emailBuilder);
}
