package com.example.core.repositories.memory;

import com.example.core.domain.Character;
import com.example.core.domain.User;
import com.example.core.repositories.UserRepository;

import java.util.ArrayList;

public class InMemoryUserRepository implements UserRepository {
    private final ArrayList<User> users;

    public InMemoryUserRepository() {
        this.users = new ArrayList<>();
    }

    @Override
    public User getUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public boolean createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (users.contains(user)) {
            return false;
        }

        return users.add(user);
    }

    @Override
    public boolean createCharacter(String username, Character character) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        if (character == null) {
            throw new IllegalArgumentException("Character cannot be null");
        }

        User user = getUser(username);

        return user.addCharacter(character);
    }
}
