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
    ProjectMapper projectMapper = new ProjectMapper();
    String selectStatement = "select project.*, GROUP_CONCAT(user_has_project.user_id SEPARATOR ',') as assigned_user_ids from project "
                            + " JOIN user_has_project on project.project_id = user_has_project.project_id "
                            + " GROUP BY project.project_id";

    public void createProject(Project project) {
        try {
            Connection con = DBManager.getConnection();
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
                for (int i = 0; i < project.getAssignedUserIds().length; i++) {
                    SQL = "INSERT INTO user_has_project (user_id, project_id) VALUES (?, ?)";
                    ps = con.prepareStatement(SQL);
                    ps.setInt(1, (int) Array.get(project.getAssignedUserIds(), i));
                    ps.setInt(2, project.getProjectId());
                    ps.executeUpdate();
                }
            } catch (Exception e){
                con.rollback();
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Project> getProjectList(){
        ArrayList<Project> projectList = new ArrayList<>();
        try{
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(selectStatement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Project project = projectMapper.mapRow(rs);
                PreparedStatement psUserIds = con.prepareStatement("SELECT * FROM user_has_project WHERE project_id = ?");
                psUserIds.setInt(1, project.getProjectId());
                ResultSet rsUserIds = ps.executeQuery();
                projectList.add(project);
            }

        }catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
        return projectList;
    }

    public Project getProject (int projectId){
        try{
            Connection con = DBManager.getConnection();
            String SQL = selectStatement
                    + "WHERE project_id=?";
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

}
