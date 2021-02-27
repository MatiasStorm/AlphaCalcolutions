package easyon.alphacalcolutions.data.mapper;

import easyon.alphacalcolutions.model.UserTitle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTitleMapper {


        public UserTitle mapRow(ResultSet rs) throws SQLException {
            UserTitle userTitle = new UserTitle();
            userTitle.setUserTitleId(rs.getInt("user_title_id"));
            userTitle.setUserTitle(rs.getString("user_title"));
            return userTitle;
        }
}
