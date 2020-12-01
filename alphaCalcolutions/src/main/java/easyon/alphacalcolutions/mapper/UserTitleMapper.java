package easyon.alphacalcolutions.mapper;

import easyon.alphacalcolutions.model.Employee;
import easyon.alphacalcolutions.model.EmployeeTitle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTitleMapper {


        public EmployeeTitle mapRow(ResultSet rs) throws SQLException {
            EmployeeTitle employeeTitle = new EmployeeTitle();
            employeeTitle.setEmployeeTitleId(rs.getInt("employee_title_id"));
            employeeTitle.setEmployeeTitle(rs.getString("employee_title"));
            return employeeTitle;
        }
}
