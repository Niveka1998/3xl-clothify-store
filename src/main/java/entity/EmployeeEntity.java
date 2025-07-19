package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Entity
//@Table(name = "employee")

public class EmployeeEntity {
    //@Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    private int id;
    private String name;
    private String email;
    private String password;
}
