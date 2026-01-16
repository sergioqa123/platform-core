package repository;

import domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<User> userList = new ArrayList<>();
    private int userId = 1;

    public void saveUser(User user){
        user.setId(userId);
        userId++;
        this.userList.add(user);
    }

    public void removeUser(User user){
        userList.remove(user);
    }

    public List<User> getUsers(){
        return userList;
    }
}
