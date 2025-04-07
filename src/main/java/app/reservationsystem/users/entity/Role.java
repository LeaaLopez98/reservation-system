package app.reservationsystem.users.entity;

public enum Role {
    OWNER, PLAYER, ADMIN;

    public String getRole(){
        return String.format("ROLE_%s", this.name());
    }
}
