package Edu.icet.Entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity

public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String Productname;
    private String Prise;
    private String Imgsrc;
    private String type;
    private String color;
    private String avalible;
}
