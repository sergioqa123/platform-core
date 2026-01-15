# Platform Core

User and course management system built with Java.

## Description

Console application for managing users (students and instructors) and courses. Includes basic CRUD operations and instructor assignment to courses.

## Features

- User registration, listing, update and deletion
- Course registration, listing, update and deletion
- Instructor assignment to courses
- Student enrollment in courses

## Project Structure

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

## How to Run

Compile and run the `Main.java` class.

```
javac Main.java
java Main
```

## Technologies

- Java