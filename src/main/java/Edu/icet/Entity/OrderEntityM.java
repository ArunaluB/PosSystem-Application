package Edu.icet.Entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class OrderEntityM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
