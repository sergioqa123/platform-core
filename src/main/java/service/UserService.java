package service;

import domain.User;
import repository.UserRepository;

import java.util.ArrayList;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void registerUser(User user){
        userRepository.saveUser(user);
    }

    public ArrayList<User> listUsers(){
        return userRepository.getUsers();
    }

}
