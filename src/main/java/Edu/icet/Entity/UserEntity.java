package Edu.icet.Entity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class UserEntity {
    @Id
    private String id;
    private String Name;
    private String Email;
    private String password;
    private String Type;


}
