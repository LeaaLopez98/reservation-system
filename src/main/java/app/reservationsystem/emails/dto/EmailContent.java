package app.reservationsystem.emails.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class EmailContent {

    private String to;
    private String subject;
    private String htmlTemplatePath;
    private Map<String, Object> model;

}
