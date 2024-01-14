package Edu.icet.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class BillFinalDetals {
    private String orderid;
    private String Email ;
    private String phonenumber ;
    private Double TotalBillprise ;
}
