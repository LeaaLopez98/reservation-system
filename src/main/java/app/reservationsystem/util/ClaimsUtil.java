package app.reservationsystem.util;

import app.reservationsystem.persistence.entity.Role;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class ClaimsUtil {

    private static Claims getClaims() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getDetails();

        return (Claims) principal;
    }

    public static Long getUserId() {
        Claims claims = getClaims();
        return Long.valueOf(claims.getId());
    }

    public static Role getRole() {
        Claims claims = getClaims();
        return Role.valueOf(claims.get("role", String.class));
    }

}
