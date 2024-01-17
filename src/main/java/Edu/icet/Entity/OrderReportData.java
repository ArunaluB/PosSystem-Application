package Edu.icet.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity

public class OrderReportData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderId;
    private String productName;
    private String date;
    private float subtotal;

    public OrderReportData(String orderId, String productName, String date, float subtotal) {
        this.orderId = orderId;
        this.productName = productName;
        this.date = date;
        this.subtotal = subtotal;
    }
}
