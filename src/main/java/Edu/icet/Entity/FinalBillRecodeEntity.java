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
public class FinalBillRecodeEntity {
    @Id
    private String orderid;
    private String Email ;
    private String phonenumber ;
    private Double TotalBillprise ;


}
