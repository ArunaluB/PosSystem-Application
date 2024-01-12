package Edu.icet.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class OrderDto {
    private String OrderId;
    private String Name;
    private String Email;
    private String phonenumber;
    private String note;
    private String date ;
    private String type;
    private String Status;

}
