package domain;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private String name;
    private String description;
    private User instructor;
    private final List<User> students;
    private boolean status; // Has instructor

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public User getInstructor() {
        return instructor;
    }

    public List<User> getStudents() {
        return students;
    }

    public void addStudent(User student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Description: " + description +
                ", Instructor: " + (instructor == null ? "Not assigned" : instructor.getName()) +
                ", Students: " + getStudents().size() +
                ", Status: " + (status ? "Active" : "Not active");
    }
}