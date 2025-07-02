package repository.custom.impl;

import entity.UserEntity;
import repository.custom.UserRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public UserEntity findByEmail(String email) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM users WHERE email = ?", email);
        if (rs.next()) {
            return new UserEntity(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
            );
        }
        return null;
    }

    @Override
    public boolean save(UserEntity user) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO users (email, password, role) VALUES (?, ?, ?)",
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}
