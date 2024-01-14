package Edu.icet.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class PayBillDto {

    private long id;
    private String orderid;
    private String Date;
    private double TotalPrise ;
}

