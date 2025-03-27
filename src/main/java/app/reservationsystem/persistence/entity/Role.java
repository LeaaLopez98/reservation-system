package app.reservationsystem.persistence.entity;

public enum Role {
    OWNER, PLAYER, ADMIN;

    public String getRole(){
        return String.format("ROLE_%s", this.name());
    }
}
