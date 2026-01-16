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

    public List<User> getUsers(){
        return userList;
    }

    public void removeUser(User user){
        userList.remove(user);
    }

    public void updateUser(User selectedUser, String newName, String newEmail){
        selectedUser.setName(newName);
        selectedUser.setEmail(newEmail);
    }

    public User getUserById(int id){
        for (User u : userList){
            if (u.getId() == id){
                return u;
            }
        }
        return null;
    }
}
