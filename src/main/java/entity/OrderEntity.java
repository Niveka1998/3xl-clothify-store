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
    private int employeeId;
    private String customerEmail;
    private double totalCost;
    private String paymentType;
    private List<Product> items;


}
