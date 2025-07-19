package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private int orderId;
    private int employeeId;
    private String employeeName;
    private double totalCost;
    private String paymentType;
    private List<Product> items;
}
