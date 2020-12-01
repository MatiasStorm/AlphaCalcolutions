package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.data.DBManager;
import easyon.alphacalcolutions.mapper.UserMapper;
import easyon.alphacalcolutions.mapper.UserTitleMapper;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;

import java.sql.*;
import java.util.ArrayList;


public class UserDAO {
    UserMapper userMapper = new UserMapper();
    UserTitleMapper userTitleMapper = new UserTitleMapper();

    String selectStatement = "SELECT * FROM employees " +
            "INNER JOIN employee_title ON employees.employee_title_id = employee_title.employee_title_id";

    public void createUser(User user){
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO employees (employee_first_name, employee_last_name, employee_title_id, employee_hourly_salary) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getTitle().getUserTitleId());
            ps.setInt(4, user.getHourlySalary());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setUserId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<User> getUserList(){
        ArrayList<User> userList = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String SQL = selectStatement;
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = userMapper.mapRow(rs);
                user.setTitle(userTitleMapper.mapRow(rs));
                userList.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;   //Hvordan kan Tine slippe afsted med return højere oppe ?
    }

    public User getUser(int userId){
        try {
            Connection con = DBManager.getConnection();
            String SQL = selectStatement
                    + " WHERE employee_id=?";
            PreparedStatement ps = con.prepareStatement(SQL);
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
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM employee_title";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userTitleList.add( userTitleMapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userTitleList;   //Hvordan kan Tine slippe afsted med return højere oppe ?
    }


}
