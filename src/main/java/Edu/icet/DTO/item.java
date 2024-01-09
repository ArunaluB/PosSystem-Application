package Edu.icet.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class item {
    private String Productname;
    private String imgsrc;
    private double prise;
    private String color;
    private String avalable ;
}
