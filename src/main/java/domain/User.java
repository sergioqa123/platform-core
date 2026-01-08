package domain;

public class User {
    private String name;
    private String email;
    private boolean status;
    private String role;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }
}
