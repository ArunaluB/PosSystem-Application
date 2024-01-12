package Edu.icet.Entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="Item")
public class ItemEntity {

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
}
