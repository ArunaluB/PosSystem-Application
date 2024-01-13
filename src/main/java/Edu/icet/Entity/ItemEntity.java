package Edu.icet.Entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="Item")
public class ItemEntity {

    // 1st step all ags contruter aka hadagannawa
    // forien key aka hadanawa  me paththo godak nathi nisa aka variyable akk many nam list akk anaw
    //joincoloum  anee ana kay aka ana paththata


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NaturalId(mutable = true)
    @Column(name = "productname")
    private String Productname;
    private Double Prise;
    private String Imgsrc;
    private String type;
    private String color;
    private String avalible;

    public ItemEntity(long id, String productname, Double prise, String imgsrc, String type, String color, String avalible) {
        this.id = id;
        Productname = productname;
        Prise = prise;
        Imgsrc = imgsrc;
        this.type = type;
        this.color = color;
        this.avalible = avalible;
    }

    @OneToMany(mappedBy = "item")
    private List<OrderdetailsEntity> orders = new ArrayList<>();
}
