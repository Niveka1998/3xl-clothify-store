package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supplier {
    private int id;
    private String name;
    private String company;
    private String email;
    private String item;
}
