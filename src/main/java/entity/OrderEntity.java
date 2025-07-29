package entity;

import dto.Product;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEntity {
    @Id
    private int orderId;
    private int userId;
    private String date;
}
