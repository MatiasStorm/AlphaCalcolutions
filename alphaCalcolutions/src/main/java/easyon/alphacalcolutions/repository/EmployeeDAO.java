package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.data.DBManager;
import easyon.alphacalcolutions.model.Employee;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {

    public void createEmployee(Employee employee){
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO employees (employee_first_name, employee_last_name, employee_title_id, employee_hourly_salary) VALUES (?, ?, ?, ?)";
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

    public ArrayList<Employee> getEmployeeList(){
        ArrayList<Employee> employeeList = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM employees ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int employeeId = rs.getInt("employee_id");
                    String firstName = rs.getString("employee_first_name");
                    String lastName = rs.getString("employee_last_name");
                    int titleId = rs.getInt("employee_title_id");
                    int hourlySalary = rs.getInt("employee_hourly_salary");
                    Employee employee = new Employee(employeeId, firstName, lastName, titleId, hourlySalary);
                    employeeList.add(employee);
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employeeList;   //Hvordan kan Tine slippe afsted med return højere oppe ?
    }

    public Employee getEmployee(int employeeId){
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM employees "
                    + "WHERE employee_id=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("employee_id");
                String firstName = rs.getString("employee_first_name");
                String lastName = rs.getString("employee_last_name");
                int titleId = rs.getInt("employee_title_id");
                int hourlySalary = rs.getInt("employee_hourly_salary");
                Employee employee = new Employee(id, firstName, lastName, titleId, hourlySalary);
                return employee;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
