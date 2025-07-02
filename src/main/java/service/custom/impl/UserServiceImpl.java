package service.custom.impl;

import dto.User;
import entity.UserEntity;
import javafx.scene.control.Alert;
import repository.custom.UserRepository;
import repository.custom.impl.UserRepositoryImpl;
import service.custom.UserService;

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
        String hashedPassword = user.getPassword(); // you can hash it
        return userRepository.save(new UserEntity(0, user.getEmail(), hashedPassword, user.getRole()));
    }
}
