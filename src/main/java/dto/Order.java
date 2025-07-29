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
    private int userId;
    private String date;
    private List<OrderDetails> items;
}
