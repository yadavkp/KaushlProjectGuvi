package com.user.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.user.Model.User;

public class UserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/userDb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Mysql@618";
    private static final String INSERT_USERS_SQL = "INSERT INTO UserKaushl (uname, email, country, passwd) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM UserKaushl WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM Userkaushl";
    private static final String DELETE_USERS_SQL = "DELETE FROM UserKaushl WHERE id = ?";
    private static final String UPDATE_USERS_SQL = "UPDATE UserKaushl SET uname = ?, email = ?, country = ?, passwd = ? WHERE id = ?";

    public UserDao() {}

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement prepared = connection.prepareStatement(INSERT_USERS_SQL)) {
            prepared.setString(1, user.getName());
            prepared.setString(2, user.getEmail());
            prepared.setString(3, user.getCountry());
            prepared.setString(4, user.getPassword());
            prepared.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement prepared = connection.prepareStatement(SELECT_USER_BY_ID)) {
            prepared.setInt(1, id);
            ResultSet resultSet = prepared.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("uname"));
                user.setEmail(resultSet.getString("email"));
                user.setCountry(resultSet.getString("country"));
                user.setPassword(resultSet.getString("passwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement prepared = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = prepared.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String uname = resultSet.getString("uname");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                String password = resultSet.getString("passwd");
                users.add(new User(id, uname, email, country, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int id) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement prepared = connection.prepareStatement(DELETE_USERS_SQL)) {
            prepared.setInt(1, id);
            status = prepared.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean updateUser(User user) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement prepared = connection.prepareStatement(UPDATE_USERS_SQL)) {
            prepared.setString(1, user.getName());
            prepared.setString(2, user.getEmail());
            prepared.setString(3, user.getCountry());
            prepared.setString(4, user.getPassword());
            prepared.setInt(5, user.getId());
            status = prepared.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        UserDao dao = new UserDao();
        if (dao.getConnection() != null) {
            System.out.println("Successfully connected to the database");
        } else {
            System.out.println("Problem in database connection!!");
        }
        User user = new User(1, "test", "test@abc.com", "India", "abc@123");
        dao.insertUser(user);
    }
}
