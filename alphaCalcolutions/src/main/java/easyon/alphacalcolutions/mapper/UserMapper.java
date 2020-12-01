package easyon.alphacalcolutions.mapper;

import easyon.alphacalcolutions.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public Employee mapRow(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setFirstName(rs.getString("employee_first_name"));
        employee.setLastName(rs.getString("employee_last_name"));
        employee.setHourlySalary(rs.getInt("employee_hourly_salary"));
        return employee;
    }

}
