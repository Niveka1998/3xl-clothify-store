package service.custom;

import dto.User;

import java.sql.SQLException;

public interface UserService {
    User login(String email, String password) throws SQLException;
    boolean register(User userDTO) throws SQLException;
}
