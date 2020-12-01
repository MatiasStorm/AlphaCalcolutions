package easyon.alphacalcolutions.mapper;

import easyon.alphacalcolutions.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("user_first_name"));
        user.setLastName(rs.getString("user_last_name"));
        user.setHourlySalary(rs.getInt("user_hourly_salary"));
        return user;
    }

}
