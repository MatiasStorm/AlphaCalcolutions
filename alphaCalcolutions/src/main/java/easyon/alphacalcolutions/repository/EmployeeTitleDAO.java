package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.data.DBManager;
import easyon.alphacalcolutions.model.EmployeeTitle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeTitleDAO {

    public ArrayList<EmployeeTitle> getEmployeeTitleList(){
        ArrayList<EmployeeTitle> employeeTitleList = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM employee_title";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeTitleId = rs.getInt("employee_title_id");
                String employeeTitleName = rs.getString("employee_title");
                EmployeeTitle employeeTitle = new EmployeeTitle(employeeTitleId, employeeTitleName);
                employeeTitleList.add(employeeTitle);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employeeTitleList;   //Hvordan kan Tine slippe afsted med return højere oppe ?
    }
}
