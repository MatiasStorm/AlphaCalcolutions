package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.data.DBManager;
import easyon.alphacalcolutions.mapper.ProjectMapper;
import easyon.alphacalcolutions.model.Project;

import javax.xml.transform.Result;
import java.lang.reflect.Array;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ProjectDAO {
    private ProjectMapper projectMapper = new ProjectMapper();
    private final Connection con;
    private String selectStatement = "select project.*, GROUP_CONCAT(user_has_project.user_id SEPARATOR ',') as assigned_user_ids from project "
                           + " JOIN user_has_project on project.project_id = user_has_project.project_id ";

    public ProjectDAO(Connection con){
        this.con = con;
    }

    public void createProject(Project project) {
        try {
            con.setAutoCommit(false);
            String SQL = "INSERT INTO project (project_title, project_start_date, project_end_date, project_leader_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            ps.setString(1, project.getTitle());
            ps.setString(2, dateFormat.format(project.getStartDate()));
            ps.setString(3, dateFormat.format(project.getEndDate()));
            ps.setInt(4, project.getProjectLeaderId());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            project.setProjectId(id);

            try {
                insertAssignedUsers(project.getAssignedUserIds(), project.getProjectId());
            } catch (Exception e){
                con.rollback();
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertAssignedUsers(int[] assignedUserIds, int projectId) throws SQLException {
        String insertStatement = "INSERT INTO user_has_project (user_id, project_id) VALUES (?, ?)";
        PreparedStatement ps;
        for (int i = 0; i < assignedUserIds.length; i++) {
            ps = con.prepareStatement(insertStatement);
            ps.setInt(1, (int) Array.get(assignedUserIds, i));
            ps.setInt(2, projectId);
            ps.executeUpdate();
        }
    }

    private void deleteAssignedUsers(int projectId) throws SQLException {
        String deleteStatement = "DELETE FROM user_has_project WHERE project_id = ?";
        PreparedStatement ps = con.prepareStatement(deleteStatement);
        ps.setInt(1, projectId);
        ps.executeUpdate();
    }

    public ArrayList<Project> getProjectList(){
        ArrayList<Project> projectList = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement(createSelect(" "));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Project project = projectMapper.mapRow(rs);
                projectList.add(project);
            }

        }catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
        return projectList;
    }

    private String createSelect(String where){
        return selectStatement + where + " GROUP BY project.project_id";
    }

    public Project getProject (int projectId){
        try{
            String SQL =  createSelect(" WHERE project.project_id=?");
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Project project = projectMapper.mapRow(rs);
                return project;
            }
        }catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public void updateProject(Project project) {
        try {
            con.setAutoCommit(false);
            String SQL = "UPDATE project SET project_title = ?, project_start_date=?, project_end_date=?, project_leader_id=? WHERE project_id=?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            ps.setString(1, project.getTitle());
            ps.setString(2, dateFormat.format(project.getStartDate()));
            ps.setString(3, dateFormat.format(project.getEndDate()));
            ps.setInt(4, project.getProjectLeaderId());
            ps.setInt(5, project.getProjectId());
            ps.executeUpdate();
            try {
                deleteAssignedUsers(project.getProjectId());
                insertAssignedUsers(project.getAssignedUserIds(), project.getProjectId());
            } catch (Exception e){
                con.rollback();
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteProject(int projectId) {
        try {
            String deleteStatement = "DELETE FROM project WHERE project_id = ?";
            PreparedStatement ps = con.prepareStatement(deleteStatement);
            ps.setInt(1, projectId);
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
