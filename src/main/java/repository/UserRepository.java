package repository;

import domain.User;

import java.util.ArrayList;

public class UserRepository {

    private ArrayList<User> userList = new ArrayList<>();
    private int userId = 1;

    public void saveUser(User user){
        user.setId(userId);
        userId++;
        this.userList.add(user);
    }

    public ArrayList<User> getUsers(){
        return userList;
    }
}
