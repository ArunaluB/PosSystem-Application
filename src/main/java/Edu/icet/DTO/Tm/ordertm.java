package Edu.icet.DTO.Tm;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ordertm {
    private String OrderId;
    private String Name;
    private String date;
    private String type;
    private String note;

//    private StringProperty orderid;
//    private StringProperty customerName;
//    private StringProperty type;
//    private StringProperty note;
//    private StringProperty date;
//    private Button processButton;
    private Button getprocessButton;


}
