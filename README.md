# Platform Core

Console application for managing users and courses. Built with pure Java following a layered architecture.

## Description

System that allows managing students and instructors, creating courses, assigning instructors to courses, and enrolling students. Implements CRUD operations for users and courses through a console menu.

## Features

- User registration and management (students and instructors)
- Course registration and management
- Instructor assignment to courses
- Student enrollment in courses

## Structure

```
src/main/java/
    Main.java
    domain/
        User.java
        Course.java
        Role.java
    repository/
        UserRepository.java
        CourseRepository.java
    service/
        UserService.java
        CourseService.java
```

## Technologies

- Java