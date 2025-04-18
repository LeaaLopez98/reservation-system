package app.reservationsystem.emails.builder;

import app.reservationsystem.emails.dto.EmailContent;

public interface EmailBuilder {
    EmailContent buildEmail();
}
