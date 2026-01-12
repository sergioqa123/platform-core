package service;

import domain.User;
import domain.Role;
import repository.UserRepository;

import java.util.ArrayList;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public boolean isValidRole(String roleInput){
        return roleInput.equals("1") || roleInput.equals("2");
    }

    public ArrayList<User> getAllUsers(){
        return userRepository.getUsers();
    }

    public ArrayList<User> getAllUsersByRole(Role role){
        ArrayList<User> usersWithRole = new ArrayList<>();
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
}
