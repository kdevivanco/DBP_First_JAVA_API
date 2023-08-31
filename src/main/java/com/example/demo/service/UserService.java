package com.example.demo.service;

import com.example.demo.api.model.User;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

        User user1 = new User(1,"Ida", 32, "ida@mail.com");
        User user2 = new User(2,"Hans", 26, "hans@mail.com");
        User user3 = new User(3,"Lars", 45, "lars@mail.com");
        User user4 = new User(4,"Ben", 32, "ben@mail.com");
        User user5 = new User(5,"Eva", 59, "eva@mail.com");

        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }

    public Optional<User> getUser(Integer id) {
        Optional<User> optional = Optional.empty();
        for (User user: userList) {
            if(id == user.getId()){
                System.out.print("User found: " + user.getName() + "\n");
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }

    public Boolean deleteUser(Integer id){
        for (User user: userList) {
            if(id == user.getId()){
                System.out.print("User found: " + user.getName() + "\n");
                userList.remove(user);
                return true;
            }
        }
        return false;
    }

    public void createUser(User user){
        userList.add(user);
    }

    public void updateUser(User user){
        for (User u: userList) {
            if(user.getId() == u.getId()){
                System.out.print("User found: " + u.getName() + "\n");
                u.setName(user.getName());
                u.setAge(user.getAge());
                u.setEmail(user.getEmail());
            }
        }
    }
}
