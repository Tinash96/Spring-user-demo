package com.example.demo.service;

import com.example.demo.repo.FakeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final FakeRepo fakeRepo;

    // Constructor Injection
    @Autowired
    public UserServiceImpl(FakeRepo fakeRepo) {
        this.fakeRepo = fakeRepo;
    }

    @Override
    public String addUser(String name, String surname) {
        long id = (long) (Math.random() * 10000);  // Random id generation
        return fakeRepo.insertUser(id, name, surname);
    }

    @Override
    public String removeUser(long id) {
        return fakeRepo.deleteUser(id);
    }

    @Override
    public String getUser(long id) {
        return fakeRepo.findUserById(id);
    }
}
