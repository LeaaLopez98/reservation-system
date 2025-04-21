package app.reservationsystem.emails.builder;

import app.reservationsystem.emails.dto.EmailContent;
import app.reservationsystem.users.entity.UserAccount;

import java.util.HashMap;
import java.util.Map;

public class ConfirmRegistrationBuilder implements EmailBuilder {

    private final UserAccount user;

    public ConfirmRegistrationBuilder(UserAccount user) {
        this.user = user;
    }

    public EmailContent buildEmail() {

        Map<String, Object> model = new HashMap<>();
        model.put("username", user.getUsername());

        return EmailContent
                .builder()
                .to(user.getEmail())
                .subject("Confirm your registration")
                .htmlTemplatePath("templates/registrationConfirmation.html")
                .model(model)
                .build();
    }

}
