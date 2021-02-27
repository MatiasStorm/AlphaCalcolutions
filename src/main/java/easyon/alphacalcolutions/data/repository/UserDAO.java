package easyon.alphacalcolutions.data.repository;

import easyon.alphacalcolutions.data.mapper.UserMapper;
import easyon.alphacalcolutions.data.mapper.UserTitleMapper;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class UserDAO {
    private final Connection con;
    private UserMapper userMapper = new UserMapper();
    private UserTitleMapper userTitleMapper = new UserTitleMapper();

    private String getSelectStatement(String whereClause){
        String selectStatement = "SELECT * FROM user " +
                                " LEFT JOIN user_title ON user.user_title_id = user_title.user_title_id ";
        String orderBy = " ORDER BY user_title.user_title";
        return selectStatement + whereClause + orderBy;
    }

    private String getSelectStatement(){
        return getSelectStatement("");
    }

    public UserDAO(Connection con){
        this.con = con;
    }

    public User login(String username, String password){
        User user = null;
        try{
            String selectStatement = getSelectStatement(" WHERE user_username=? AND user_password=?");
            PreparedStatement ps = con.prepareStatement(selectStatement, ResultSet.TYPE_SCROLL_SENSITIVE);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                user = userMapper.mapRow(rs);
                user.setTitle(userTitleMapper.mapRow(rs));
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return user;
    }

    public User createUser(User user){
        try {
            String SQL = "INSERT INTO user (user_first_name, user_last_name, user_title_id, user_hourly_salary, user_username, user_password, is_admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getTitle().getUserTitleId());
            ps.setInt(4, user.getHourlySalary());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setBoolean(7, user.getAdmin());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setUserId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getUserList(){
        ArrayList<User> userList = new ArrayList<>();
        try {
            String selectStatement = getSelectStatement();
            PreparedStatement ps = con.prepareStatement(selectStatement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = userMapper.mapRow(rs);
                user.setTitle(userTitleMapper.mapRow(rs));
                userList.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public User getUserById(int userId){
        try {
            String selectStatement = getSelectStatement("WHERE user_id=?");
            PreparedStatement ps = con.prepareStatement(selectStatement);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                User user = userMapper.mapRow(rs);
                user.setTitle(userTitleMapper.mapRow(rs));
                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserTitle> getUserTitleList(){
        ArrayList<UserTitle> userTitleList = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM user_title";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userTitleList.add( userTitleMapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userTitleList;
    }

    public ArrayList<User> getUsersByIds(int[] userIds){
        ArrayList<User> userList = new ArrayList<>();
        try {
            String inSql = '(' + String.join(",", Collections.nCopies(userIds.length, "?")) + ") ";
            String whereClause = "WHERE user_id IN " + inSql;
            String selectStatement = getSelectStatement(whereClause);
            PreparedStatement ps = con.prepareStatement(selectStatement);
            for (int i = 0; i <userIds.length ; i++) {
                ps.setInt(i + 1, userIds[i]);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = userMapper.mapRow(rs);
                user.setTitle(userTitleMapper.mapRow(rs));
                userList.add(user);
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return userList;
    }

    public boolean updateUser(User user){
        try {
            String updateStatement = "UPDATE user " +
                    " SET user_first_name = ?, user_last_name = ?, user_title_id = ?, user_hourly_salary = ?, user_username = ?, user_password = ?, is_admin = ?" +
                    " WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(updateStatement);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getTitle().getUserTitleId());
            ps.setInt(4, user.getHourlySalary());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setBoolean(7, user.getAdmin());
            ps.setInt(8, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUser(int userId){
        try {
            String deleteStatement = "DELETE FROM user WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(deleteStatement);
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;

    }


    public ArrayList<User> getUserSearch(String search) {
        ArrayList<User> listOfUsers = new ArrayList<>();
        try {
            String[] searched = search.split(" ");
            String whereClause = "WHERE ";
            whereClause += "user.user_first_name LIKE ? OR user.user_last_name LIKE ? ";
            for (int i = 1; i < searched.length; i++) {
               whereClause += "OR " + "user.user_first_name LIKE ? OR user.user_last_name LIKE ? ";
            }
            String statement = getSelectStatement(whereClause);
            PreparedStatement ps = con.prepareStatement(statement);
            int i = 0;
            for (String s: searched) {
                ps.setString(i+1, "%"+s+"%");
                ps.setString(i+2, "%" +s +"%");
                i+=2;
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = userMapper.mapRow(rs);
                user.setTitle(userTitleMapper.mapRow(rs));
                listOfUsers.add(user);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOfUsers;

    }
}
