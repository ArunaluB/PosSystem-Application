package Edu.icet.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class PaymentDetailsEntity {
    @Id
    private long id;
    private String orderid;
    private String Date;
    private double TotalPrise ;
}
