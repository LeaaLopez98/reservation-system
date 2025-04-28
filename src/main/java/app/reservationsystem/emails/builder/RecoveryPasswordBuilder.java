package app.reservationsystem.emails.builder;

import app.reservationsystem.emails.dto.EmailContent;

import java.util.HashMap;
import java.util.Map;

public class RecoveryPasswordBuilder implements EmailBuilder {

    private final String token;
    private final String email;

    public RecoveryPasswordBuilder(String email, String token) {
        this.token = token;
        this.email = email;
    }

    @Override
    public EmailContent buildEmail() {

        // TODO: This have to redirect to a frontend for fill a form with the new password
        String url = String.format("http://localhost:3000/frontend?token=%s", token);

        Map<String, Object> model = new HashMap<>();
        model.put("resetPasswordUrl", url);

        return EmailContent
                .builder()
                .to(email)
                .subject("Request for recovery password")
                .htmlTemplatePath("templates/recoveryPasswordTemplate.html")
                .model(model)
                .build();
    }
}
