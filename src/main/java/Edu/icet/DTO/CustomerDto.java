package Edu.icet.DTO;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustomerDto {
    private String customerName;
    private String Email;
    private String Contactnumber;
}
