package app.reservationsystem.shared.util.constants;

public class ExceptionMessages {
    public static final String USERNAME_NOT_FOUND = "User with username %s not found";
    public static final String BAD_CREDENTIALS = "Username or password incorrect";
    public static final String USERNAME_EXISTS = "User with username already exists";
    public static final String EMAIL_EXISTS = "User with email already exists";

    public static final String CLUB_NOT_FOUND = "Club with id %s, Not found";
    public static final String FIELD_NOT_FOUND = "Field with id %s, Not found";

    public static final String CLUB_NOT_OWNER = "You are not the owner of this club";

    public static final String PLAYER_NOT_FOUND = "Player with id %s, Not found";
    public static final String OWNER_NOT_FOUND = "Owner with id %s, Not found";
    public static final String RESERVATION_NOT_FOUND = "Reservation with id %s, Not found";

    public static final String RESERVATION_FORBIDDEN = "You can't access this reservation";
    public static final String RESERVATION_NOT_OWNER = "You are not the owner of this reservation";

    public static final String RESERVATION_NOT_AVAILABLE = "Reservation not available in that period";
}
