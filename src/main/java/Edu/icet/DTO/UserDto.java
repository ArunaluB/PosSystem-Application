package Edu.icet.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {


    private String Name;
    private String Email;
    private String password;
    private String Type;


}
