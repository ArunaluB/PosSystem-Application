package Edu.icet.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String customerName;
    private String Email;
    private String Contactnumber;

    public CustomerEntity(Long id, String customerName, String email, String contactnumber) {
        this.id = id;
        this.customerName = customerName;
        Email = email;
        Contactnumber = contactnumber;
    }

    //many nisa list akk crete karay customer godak oders ana nis a serama list akk away
    // mepaththe yana nisa anith ake forign key aka dapu nama danna
    // id aka yana paththen mappedby anawa
//    @OneToMany(mappedBy = "customerfk")
//   private List<OrderEntity> ordeslist;
}
