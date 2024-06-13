package com.example.core.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private ArrayList<Character>  characters;

    public User() {}
    public User(String username, String password) {
        this.characters = new ArrayList<Character>();
        this.username = username;
        this.password = password;
    }
    public int getId() {
        return id;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean addCharacter(Character character) {
        return characters.add(character);
    }

}
