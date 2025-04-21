package app.reservationsystem.emails.service.impl;

import app.reservationsystem.emails.builder.EmailBuilder;
import app.reservationsystem.emails.dto.EmailContent;
import app.reservationsystem.emails.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.from}")
    private String fromEmail;

    @Async
    @Override
    public void sendEmail(EmailBuilder emailBuilder) {
        try {

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            EmailContent emailContent = emailBuilder.buildEmail();

            String processedHtmlTemplate = processTemplate(emailContent.getHtmlTemplatePath(), emailContent.getModel());

            helper.setTo(emailContent.getTo());
            helper.setSubject(emailContent.getSubject());
            helper.setText(processedHtmlTemplate, true);
            helper.setFrom(fromEmail);

            emailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String processTemplate(String htmlTemplate, Map<String, Object> model) {

        Context context = new Context();

        model
            .keySet()
            .forEach(key -> context.setVariable(key, model.get(key)));

        return templateEngine.process(htmlTemplate, context);

    }
}
