package com.example.demo.repo;

import com.example.demo.model.User;
import java.util.UUID;

public interface FakeRepoInterface {
    User insertUser(String name, String surname);  // no need to pass UUID here
    User findUserById(UUID id);
    boolean deleteUser(UUID id);
}
