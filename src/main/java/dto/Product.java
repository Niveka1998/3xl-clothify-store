package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Product {
    private int id;
    private String name;
    private String size;
    private double price;
    private int quantity;
    private String image_url;

}
