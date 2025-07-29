package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetails {
    private String orderId;
    private String itemCode;
    private Integer qty;
    private Double unitPrice;
}
