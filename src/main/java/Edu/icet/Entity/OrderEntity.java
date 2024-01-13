package Edu.icet.Entity;


import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class OrderEntity {
    @Id
    private String OrderId;
    @NaturalId
    @Column(name = "orderid", unique = true)
    private String orderidnatural;
    private String Name;
    private String Email;
    private String phonenumber;
    private String note;
    private String date ;
    private String Type;
    private String Status;
}
