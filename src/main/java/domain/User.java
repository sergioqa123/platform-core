package domain;

public class User {
    private int id;
    private String name;
    private String email;
    private boolean enrolled;
    private final Role role;

    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.enrolled = false;
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

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    public boolean isEnrolled() {
        return enrolled;
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
        String status = (role == Role.STUDENT ? (enrolled ? "Enrolled" : "Not enrolled") : "Instructor");
        return "ID: " + id +
                ", Name: " + name +
                ", Email: " + email +
                ", Role: " + role +
                ", Status: " + status;
    }
}
