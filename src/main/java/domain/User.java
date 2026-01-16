package domain;

public class User {
    private int id;
    private String name;
    private String email;
    private boolean status;
    private Role role;

    public User(String name, String email, Role role, boolean status) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isActive() {
        return status;
    }

    public Role getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Email: " + email +
                ", Role: " + role;
    }
}
