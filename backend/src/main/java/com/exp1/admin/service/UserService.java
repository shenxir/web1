package com.exp1.admin.service;

import com.exp1.admin.data.MockDataStore;
import com.exp1.admin.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final MockDataStore dataStore;

    public UserService(MockDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<User> getAllUsers() {
        return dataStore.getUsers();
    }
}
