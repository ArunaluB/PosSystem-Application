package Edu.icet.Entity;


import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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



    public OrderEntity(String orderId, String orderidnatural, String name, String email, String phonenumber, String note, String date, String type, String status) {
        OrderId = orderId;
        this.orderidnatural = orderidnatural;
        Name = name;
        Email = email;
        this.phonenumber = phonenumber;
        this.note = note;
        this.date = date;
        Type = type;
        Status = status;
    }
    @OneToMany(mappedBy = "order")
    private List<OrderdetailsEntity> orders = new ArrayList<>();
}
