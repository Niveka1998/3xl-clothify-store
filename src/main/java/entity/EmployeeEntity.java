package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EmployeeEntity {
    private int id;
    private String name;
    private String email;
    private String password;
}
