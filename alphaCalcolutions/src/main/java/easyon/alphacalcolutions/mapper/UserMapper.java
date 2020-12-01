package easyon.alphacalcolutions.mapper;

import easyon.alphacalcolutions.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("employee_id"));
        user.setFirstName(rs.getString("employee_first_name"));
        user.setLastName(rs.getString("employee_last_name"));
        user.setHourlySalary(rs.getInt("employee_hourly_salary"));
        return user;
    }

}
