package com.example.core.repositories.jdbc;

import com.example.core.domain.Character;
import com.example.core.domain.User;
import com.example.core.repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JdbcUserRepository implements UserRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/primordial";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;
    private Statement statement;

    public JdbcUserRepository() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    @Override
    public User getUser(String username) {
        try {
            String userQuery = "SELECT id, username, password FROM users WHERE username = ?";
            String characterQuery =
                    "SELECT c.id, c.name, c.health, c.level, c.baseDamage " +
                            "FROM characters c " +
                            "INNER JOIN user_characters uc ON c.id = uc.character_id " +
                            "WHERE uc.user_id = ?";

            User user = null;

            PreparedStatement userStmt = connection.prepareStatement(userQuery);
            PreparedStatement characterStmt = connection.prepareStatement(characterQuery);
            userStmt.setString(1, username);
            ResultSet userRs = userStmt.executeQuery();

            if (userRs.next()) {
                user = new User();
                user.setId(userRs.getInt("id"));
                user.setUsername(userRs.getString("username"));
                user.setPassword(userRs.getString("password"));
                user.setCharacters(new ArrayList<>());

                // Query associated characters
                characterStmt.setInt(1, user.getId());
                ResultSet characterRs = characterStmt.executeQuery();
                while (characterRs.next()) {
                    Character character = new Character();
                    character.setId(characterRs.getInt("id"));
                    character.setName(characterRs.getString("name"));
                    character.setHealth(characterRs.getInt("health"));
                    character.setLevel(characterRs.getInt("level"));
                    character.setBaseDamage(characterRs.getInt("baseDamage"));
                    user.getCharacters().add(character);
                }
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createUser(User user) {
        try {
            String query = "INSERT INTO users (username, password) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "')";
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean createCharacter(String username, Character character) {
        User user = getUser(username);

        if (user == null) {
            System.out.println("User not found");
            return false;
        }

        int userId = user.getId();

        String characterInsert = "INSERT INTO characters (name, health, level, baseDamage) VALUES (?, ?, ?, ?)";
        String userCharacterInsert = "INSERT INTO user_characters (user_id, character_id) VALUES (?, ?)";

        try {
             PreparedStatement charStmt = connection.prepareStatement(characterInsert, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement userCharStmt = connection.prepareStatement(userCharacterInsert);

            charStmt.setString(1, character.getName());
            charStmt.setInt(2, character.getHealth());
            charStmt.setInt(3, character.getLevel());
            charStmt.setInt(4, character.getBaseDamage());
            charStmt.executeUpdate();

            ResultSet generatedKeys = charStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int characterId = generatedKeys.getInt(1);

                userCharStmt.setInt(1, userId);
                userCharStmt.setInt(2, characterId);
                userCharStmt.executeUpdate();

                character.setId(characterId);

                return user.addCharacter(character);
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
