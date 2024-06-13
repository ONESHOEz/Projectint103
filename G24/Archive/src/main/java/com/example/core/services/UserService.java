package com.example.core.services;

import com.example.core.domain.User;
import com.example.core.domain.Character;
import com.example.core.domain.exceptions.InvalidPasswordFormatException;
import com.example.core.domain.exceptions.UserNotFoundException;
import com.example.core.repositories.UserRepository;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private UserRepository userRepository;
    private String username;
    private int guestId = 0;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password are required");
        }
        User user = userRepository.getUser(username);

        if (user == null) {
           throw new UserNotFoundException("User not found");
        }

        if (!user.getPassword().equals(password)) {
            return false;
        }

        setUsername(username);

        return true;
    }

    public boolean guestLogin() {
        boolean success = false;
        String guestUsername = "guest" + guestId++;
        success = register(guestUsername, "guFest123");
        if (!success) {
            return false;
        }
        success = login(guestUsername, "guFest123");

        return success;
    }

    public boolean register(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password are required");
        }
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}$";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(password);

        if (!matcher.matches()) {
            throw new InvalidPasswordFormatException("password is invalid");
        }

        User user = new User(username, password);

        return userRepository.createUser(user);
    }

    public ArrayList<Character> getCharacters() {
        return userRepository.getUser(username).getCharacters();
    }

    public boolean newCharacter(Character character) {
        userRepository.createCharacter(username, character);
        return true;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
