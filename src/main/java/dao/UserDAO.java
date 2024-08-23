package dao;

import entity.User;
import utils.PasswordEncoder;
import utils.ShowMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DBCon;

public class UserDAO {

    public boolean addUser(User user) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL addUser(?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, user.getId());
            callableStatement.setString(2, user.getUsername());
            callableStatement.setString(3, user.getPassword());
            callableStatement.setString(4, user.getRole());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
        	if (e.getErrorCode() == 2627) {
                ShowMessage.showWarningMessage("Warning", "Username already exists. Please choose a different Username.");
            } else {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllUsers}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public boolean updateUser(User user) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL updateUser(?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, user.getId());
            callableStatement.setString(2, user.getUsername());
            callableStatement.setString(3, user.getPassword());
            callableStatement.setString(4, user.getRole());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(String userId) throws SQLIntegrityConstraintViolationException {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL deleteUser(?)}");
        ) {
            callableStatement.setString(1, userId);

            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new SQLIntegrityConstraintViolationException("No rows affected. Probably the user with ID " + userId + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException("Error while deleting Apartment. Details: " + e.getMessage());
        }
    }

    public User getUserById(String userId) {
        User user = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getUserById(?)}");
        ) {
            callableStatement.setString(1, userId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getString("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<String> getAllUserIds() {
        List<String> userIds = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllUserIds}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                String userId = resultSet.getString("id");
                userIds.add(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userIds;
    }

    public String getUserNameById(String userId) {
        String userName = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getUserNameById(?)}");
        ) {
            callableStatement.setString(1, userId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    userName = resultSet.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userName;
    }
    
    public User loginUser(String username, String password) {
        User user = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getUserByUsername(?)}");
        ) {
            callableStatement.setString(1, username);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                	String storedPasswordHash = resultSet.getString("password");
                	if(PasswordEncoder.checkPassword(password, storedPasswordHash)) {
                		user = new User();
                        user.setId(resultSet.getString("id"));
                        user.setUsername(resultSet.getString("username"));
                        user.setRole(resultSet.getString("role"));
                	} else {
                        System.out.println("Login failed. Invalid password.");
                    }
                } else {
                    System.out.println("Login failed. User not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public boolean changePassword(String userId, String username, String newPassword, String oldPassword) {
    	User user = loginUser(username, oldPassword);
    	if(user == null) {
    		ShowMessage.showWarningMessage("Warning", "Old Password is not correct !");
    		return false;
    		}
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL changePassword(?, ?)}");
        ) {
            callableStatement.setString(1, userId);
            callableStatement.setString(2, newPassword);

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
