package domain;

public class Course {
    private int id;
    private String name;
    private String description;
    private User instructor; // Only one instructor per course
    private boolean status;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getInstructor() {
        return instructor;
    }

    public boolean isActive() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name;
    }
}