package Edu.icet.DAO.Custom;

import Edu.icet.DAO.Cruddao;

public interface Userdao extends Cruddao {
    String getPasswordByUsername(String username);
    String getSearchByUsername(String username);

    boolean updatePasswordByUsername(String username, String newPassword);

}
