package Edu.icet.Entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "orderdatails")
public class OrderdetailsEntity {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

//    // map id akata anne key ake variyable name tila
//
//    @EmbeddedId
//    private OrderCompositeKey id;
//    private int quantity;
//    private float subtotal;
//
//    @ManyToOne
//   // @MapsId("Cusid")
//    @JoinColumn(name = "id")
//    ItemEntity item;
//
//    @ManyToOne
//  //  @MapsId("orderid")
//    @JoinColumn(name = "OrderId")
//    OrderEntity order;


}
