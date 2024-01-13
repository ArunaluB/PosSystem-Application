package Edu.icet.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public class OrderCompositeKey implements Serializable {

    @Column(nullable = false)
    private String orderid;
    @Column(nullable = false)
    private long Cusid;


}
