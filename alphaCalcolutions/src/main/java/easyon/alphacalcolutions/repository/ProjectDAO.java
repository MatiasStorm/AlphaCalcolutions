package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.data.DBManager;
import easyon.alphacalcolutions.mapper.ProjectMapper;
import easyon.alphacalcolutions.model.Project;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ProjectDAO {
    ProjectMapper projectMapper = new ProjectMapper();

    String selectStatement = "SELECT * FROM projects" + "INNER JOIN users ON projects.project_leader_id = users.user_id";



    public void createProject(Project project) {
        try {
            Connection con = DBManager.getConnection();
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
            int id  = ids.getInt(1);
            project.setProjectId(id);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

//    public ArrayList<Project> getProjectList(){
//        ArrayList<Project> projectList = new ArrayList<>();
//        try{
//            Connection con = DBManager.getConnection();
//            String SQL = INDSÆT SELCTSTATEMENT
//              PreparedStatement ps = con.prepareStatement(SQL);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Project project = projectMapper.mapRow(rs);
//            }
//
//        }catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return projectList;
//    }

}
