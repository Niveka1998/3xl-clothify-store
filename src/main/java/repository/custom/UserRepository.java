package repository.custom;

import entity.UserEntity;

import java.sql.SQLException;

public interface UserRepository {
    UserEntity findByEmail(String email) throws SQLException;
    boolean save(UserEntity user) throws SQLException;
}
