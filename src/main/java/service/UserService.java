package service;

import domain.User;
import domain.Role;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void registerUser(String name, String email, String roleInput){
        Role role = null;
        if (roleInput.equals("1")){
            role = Role.STUDENT;
        }
        if (roleInput.equals("2")){
            role = Role.INSTRUCTOR;
        }
        User user = new User(name, email, role);
        userRepository.saveUser(user);
    }

    public boolean isValidRole(String roleInput){
        return roleInput.equals("1") || roleInput.equals("2");
    }

    public List<User> getAllUsers(){
        return userRepository.getUsers();
    }

    public List<User> getAllInstructors(){
        return getAllUsersByRole(Role.INSTRUCTOR);
    }

    public List<User> getEnrolledStudents(){
        List<User> enrolledStudents = new ArrayList<>();
        for (User u : getAllUsersByRole(Role.STUDENT)){
            if (u.isEnrolled()){
                enrolledStudents.add(u);
            }
        }
        return enrolledStudents;
    }

    public List<User> getAllUsersByRole(Role role){
        List<User> usersWithRole = new ArrayList<>();
        for (User u : getAllUsers()) {
            if (u.getRole() == role) {
                usersWithRole.add(u);
            }
        }
        return usersWithRole;
    }

    public User getInstructorById(int instructorId){
        for (User i : getAllUsersByRole(Role.INSTRUCTOR)){
            if (i.getId() == instructorId){
                return i;
            }
        }
        return null;
    }

    public User getUserById(int userId){
        return userRepository.getUserById(userId);
    }

    public void updateUser(User selectedUser, String newName, String newEmail){
        userRepository.updateUser(selectedUser, newName, newEmail);
    }

    public void deleteUser(User selectedUser){
        userRepository.removeUser(selectedUser);
    }

}
