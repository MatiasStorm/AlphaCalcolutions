package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.data.DBManager;
import easyon.alphacalcolutions.mapper.UserMapper;
import easyon.alphacalcolutions.mapper.UserTitleMapper;
import easyon.alphacalcolutions.model.Employee;
import easyon.alphacalcolutions.model.EmployeeTitle;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {
    UserMapper userMapper = new UserMapper();
    UserTitleMapper userTitleMapper = new UserTitleMapper();

    String selectStatement = "SELECT * FROM employees " +
            "INNER JOIN employee_title ON employees.employee_title_id = employee_title.employee_title_id";

    public void createEmployee(Employee employee){
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO employees (employee_first_name, employee_last_name, employee_title_id, employee_hourly_salary) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getTitle().getEmployeeTitleId());
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
            String SQL = selectStatement;
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee user = userMapper.mapRow(rs);
                user.setTitle(userTitleMapper.mapRow(rs));
                employeeList.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employeeList;   //Hvordan kan Tine slippe afsted med return højere oppe ?
    }

    public Employee getEmployee(int employeeId){
        try {
            Connection con = DBManager.getConnection();
            String SQL = selectStatement
                    + " WHERE employee_id=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            Employee user = userMapper.mapRow(rs);
            if(rs.next()){
                user.setTitle(userTitleMapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<EmployeeTitle> getEmployeeTitleList(){
        ArrayList<EmployeeTitle> employeeTitleList = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM employee_title";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                employeeTitleList.add( userTitleMapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employeeTitleList;   //Hvordan kan Tine slippe afsted med return højere oppe ?
    }


}
