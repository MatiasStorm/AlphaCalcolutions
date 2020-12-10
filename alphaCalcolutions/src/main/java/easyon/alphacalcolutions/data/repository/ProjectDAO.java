package easyon.alphacalcolutions.data.repository;

import easyon.alphacalcolutions.data.mapper.ProjectMapper;
import easyon.alphacalcolutions.model.Project;

import java.lang.reflect.Array;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class ProjectDAO {
    private ProjectMapper projectMapper = new ProjectMapper();
    private final Connection con;

    private String getSelectStatement(String where){
        String selectStatement = "select project.*, sub_project.assigned_user_ids, MIN(task.task_start_date) as project_start_date, MAX(task.task_end_date) as project_end_date from project " +
                "JOIN (select project.project_id, GROUP_CONCAT(user_has_project.user_id SEPARATOR ',') as assigned_user_ids from project " +
                "JOIN user_has_project ON project.project_id = user_has_project.project_id GROUP BY project.project_id) " +
                "sub_project ON sub_project.project_id = project.project_id " +
                "LEFT JOIN task ON project.project_id = task.project_id ";
        return selectStatement + where + " GROUP BY project.project_id";
    }

    private String getSelectStatement(){
        return getSelectStatement("");
    }

    public ProjectDAO(Connection con){
        this.con = con;
    }

    public void createProject(Project project) {
        try {
            con.setAutoCommit(false);
            String SQL = "INSERT INTO project (project_title, project_leader_id) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, project.getTitle());
            ps.setInt(2, project.getProjectLeaderId());
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
            PreparedStatement ps = con.prepareStatement(getSelectStatement());
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


    public Project getProject (int projectId){
        try{
            String selectStatement =  getSelectStatement(" WHERE project.project_id=?");
            PreparedStatement ps = con.prepareStatement(selectStatement);
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
            String SQL = "UPDATE project SET project_title = ?, project_leader_id=? WHERE project_id=?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, project.getTitle());
            ps.setInt(2, project.getProjectLeaderId());
            ps.setInt(3, project.getProjectId());
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
