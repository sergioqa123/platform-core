package domain;

public class User {
    private int id;
    private String name;
    private String email;
    private boolean status = true;
    private Role role;

    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return status;
    }

    public String getRole() {
        return role.name();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
