package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.FakeRepoInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final FakeRepoInterface fakeRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(FakeRepoInterface fakeRepo) {
        this.fakeRepo = fakeRepo;
    }

    @Override
    public User addUser(String name, String surname) {
        User user = new User(UUID.randomUUID(), name, surname);
        logger.info("✅ Adding user: {} {}", name, surname);
        User addedUser = fakeRepo.insertUser(user.getName(), user.getSurname());
        logger.info("✅ Added user: {} {} with ID {}", name, surname, addedUser.getId());
        return addedUser;
    }

    @Override
    public User getUser(UUID id) {
        User user = fakeRepo.findUserById(id);
        if (user != null) {
            logger.info("ℹ️ Retrieved user: {} {} with ID {}", user.getName(), user.getSurname(), id);
        } else {
            logger.warn("⚠️ User with ID {} not found", id);
        }
        return user;
    }

    @Override
    public boolean removeUser(UUID id) {
        boolean isDeleted = fakeRepo.deleteUser(id);
        if (isDeleted) {
            logger.info("🗑️ Deleted user with ID {}", id);
        } else {
            logger.warn("⚠️ No user found with ID {} to delete", id);
        }
        return isDeleted;
    }
}
