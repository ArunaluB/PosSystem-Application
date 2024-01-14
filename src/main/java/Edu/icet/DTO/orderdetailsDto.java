package Edu.icet.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class orderdetailsDto {
    private long id ;
    private String Productname;
    private Double Prise;
    private String date;
    private String Type;
    private String OrderId;
    private String Email;
    private String phonenumber;
    private int quantity;
    private float subtotal;
}
