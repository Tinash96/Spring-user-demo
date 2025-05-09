package com.example.demo.service;

public interface UserService {
    String addUser(String name, String surname);
    String removeUser(long id);
    String getUser(long id);
}
