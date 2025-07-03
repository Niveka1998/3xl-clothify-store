package service.custom.impl;

import dto.User;
import entity.UserEntity;
import javafx.scene.control.Alert;
import repository.custom.UserRepository;
import repository.custom.impl.UserRepositoryImpl;
import service.custom.UserService;
import util.ValidationUtil;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User login(String email, String password) throws SQLException {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return new User(user.getEmail(), null, user.getRole());
        }
        return null;
    }

    @Override
    public boolean register(User user) throws SQLException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            new Alert(Alert.AlertType.ERROR,"This user email already exists.").show();
            return false; // already exists
        }

        if(!ValidationUtil.isValidPassword(user.getPassword())){
            new Alert(Alert.AlertType.ERROR, "Password too weak!Must include at least 8 characters, upper/lower letters, number, and symbol.").show();
            return false;
        }
        String hashedPassword = user.getPassword();
        return userRepository.save(new UserEntity(0, user.getEmail(), hashedPassword, user.getRole()));
    }

    public boolean updatePassword(String email, String newPassword) throws SQLException {
        if(!ValidationUtil.isValidPassword(newPassword)){
            new Alert(Alert.AlertType.ERROR,"Password is too weak!").show();
            return false;
        }
        return userRepository.updatePassword(email, newPassword);
    }
}
