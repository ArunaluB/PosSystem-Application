package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface itembo extends SuperBo {

    List loadData() throws SQLException, ClassNotFoundException;

}
