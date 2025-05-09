package com.example.demo.repo;

import com.example.demo.model.User;

import java.util.ArrayList;
import java.util.List;

public class FakeRepo implements FakeRepoInterface {
    private List<User> users = new ArrayList<>();

    @Override
    public String insertUser(long id, String name, String surname) {
        User user = new User(id, name, surname);
        users.add(user);
        return name + " added";
    }

    @Override
    public String findUserById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user.getName() + " " + user.getSurname();
            }
        }
        return "User not found";
    }

    @Override
    public String deleteUser(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return user.getName() + " removed";
            }
        }
        return "User not found";
    }
}
