package com.example.core.repositories.file;

import com.example.core.domain.Character;
import com.example.core.domain.User;
import com.example.core.repositories.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUserRepository implements UserRepository {
    private final String filename = "user.txt";
    private ArrayList<User> users;

    public FileUserRepository() {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(filename);
            in = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) in.readObject();
        } catch (FileNotFoundException f) {
            users = null;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Person class not found");
            c.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (fileIn != null) fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        if (users == null) {
            users = new ArrayList<>();
        }
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

        users.add(user);

        return writeUserToFile();
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

        user.addCharacter(character);

        return writeUserToFile();
    }

    private boolean writeUserToFile(){
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            fileOut = new FileOutputStream(filename);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            System.out.println("Serialized data is saved in " + filename);
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } finally {
            try {
                if (out != null) out.close();
                if (fileOut != null) fileOut.close();
            } catch (IOException i) {
                i.printStackTrace();
                return false;
            }
        }

        return true;
    }


}
