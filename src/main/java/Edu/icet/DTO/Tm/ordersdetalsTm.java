package Edu.icet.DTO.Tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class ordersdetalsTm {
    private String itemname;
    private double UnitPrise;

    private int Quantity;
    private double amount;


}
