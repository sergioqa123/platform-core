package repository;

import domain.User;

import java.util.ArrayList;

public class UserRepository {

    private ArrayList<User> userList = new ArrayList<>();

    public void saveUser(User user){
        this.userList.add(user);
    }

    public ArrayList<User> getUsers(){
        return userList;
    }
}
