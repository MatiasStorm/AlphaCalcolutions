package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.data.DBManager;
import easyon.alphacalcolutions.model.Employee;
import org.springframework.stereotype.Component;

import java.sql.*;


public class EmployeeDAO {

    public void createEmployee(Employee employee){
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO empolyees (employee_first_name, employee_last_name, employee_title_id, employee_hourly_salary) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getTitleId());
            ps.setInt(4, employee.getHourlySalary());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            employee.setEmployeeId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
