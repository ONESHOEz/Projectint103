package com.example.core.repositories;

import com.example.core.domain.Character;
import com.example.core.domain.User;

public interface UserRepository {
    public User getUser(String username);
    public boolean createUser(User user);
    public boolean createCharacter(String username, Character character);
}