package app.reservationsystem.emails.builder;

import app.reservationsystem.emails.dto.EmailContent;
import app.reservationsystem.reservations.entity.Reservation;

import java.util.HashMap;
import java.util.Map;

public class ConfirmReservationBuilder implements EmailBuilder {

    private final Reservation reservation;

    public ConfirmReservationBuilder(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public EmailContent buildEmail() {

        // TODO: This have to redirect to a frontend for fill a form with the new password
        String url = String.format("http://localhost:8080/api/reservations/%s/confirm", reservation.getIdReservation());

        Map<String, Object> model = new HashMap<>();
        model.put("clubName", reservation.getField().getClub().getName());
        model.put("playerName", reservation.getPlayer().getName());
        model.put("fieldNumber", reservation.getField().getFieldNumber());
        model.put("clubAddress", reservation.getField().getClub().getAddress());
        model.put("confirmationUrl", url);
        model.put("dateBegin", reservation.getDateBegin());
        model.put("dateEnd", reservation.getDateEnd());

        return EmailContent
                .builder()
                .to(reservation.getPlayer().getEmail())
                .subject("Confirm your reservations")
                .htmlTemplatePath("templates/confirmReservationTemplate.html")
                .model(model)
                .build();
    }
}
