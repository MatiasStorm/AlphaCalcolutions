package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.data.DBManager;
import easyon.alphacalcolutions.mapper.ProjectMapper;
import easyon.alphacalcolutions.model.Project;

import java.sql.*;

public class ProjectDAO {
    ProjectMapper projectMapper = new ProjectMapper();

    public void createProject(Project project) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO projects (project_title, project_start_date, project_end_date, project_leader_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, project.getTitle());
            ps.setDate(2, (Date) project.getStartDate());
            ps.setDate(3, (Date) project.getEndDate());
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


}
