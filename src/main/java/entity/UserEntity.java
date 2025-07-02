package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserEntity {
    private int id;
    private String email;
    private String password;
    private String role;
}
