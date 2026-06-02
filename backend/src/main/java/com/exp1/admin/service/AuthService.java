package com.exp1.admin.service;

import com.exp1.admin.data.MockDataStore;
import com.exp1.admin.model.ChangePasswordRequest;
import com.exp1.admin.model.LoginRequest;
import com.exp1.admin.model.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MockDataStore dataStore;

    public AuthService(MockDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public boolean login(LoginRequest request) {
        String password = dataStore.getRegisteredUsers().get(request.getUsername());
        return password != null && password.equals(request.getPassword());
    }

    public boolean register(RegisterRequest request) {
        if (dataStore.getRegisteredUsers().containsKey(request.getUsername())) {
            return false;
        }
        dataStore.getRegisteredUsers().put(request.getUsername(), request.getPassword());
        return true;
    }

    public boolean changePassword(ChangePasswordRequest request) {
        String oldPassword = dataStore.getRegisteredUsers().get(request.getUsername());
        if (oldPassword == null || !oldPassword.equals(request.getOldPassword())) {
            return false;
        }
        dataStore.getRegisteredUsers().put(request.getUsername(), request.getNewPassword());
        return true;
    }
}
