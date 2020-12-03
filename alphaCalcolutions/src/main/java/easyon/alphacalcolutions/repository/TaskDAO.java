package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.mapper.TaskMapper;
import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;

import java.lang.reflect.Array;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TaskDAO {

    private TaskMapper taskMapper = new TaskMapper();
    private final Connection con;
    //husk at ændre til task
    private String selectStatement = "select task.*, GROUP_CONCAT(user_has_task.user_id SEPARATOR ',') as assigned_user_ids " +
            "from task " +
            "JOIN user_has_task on task.task_id = user_has_task.task_id " +
            "GROUP BY task.task_id;";

//    select task.*,  GROUP_CONCAT(task_has_dependency.dependant_task_id SEPARATOR ',')
//    from task
//    left join task_has_dependency on task.task_id = task_has_dependency.dependency_task_id
//    GROUP BY task.task_id;

    public TaskDAO(Connection con) {
        this.con = con;
    }

    public void createTask(Task task) {
        try {
            con.setAutoCommit(false);
            String SQL = "INSERT INTO task (task_title, task_leader_id, task_start_date, task_end_date, project_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            ps.setString(1, task.getTitle());
            ps.setInt(2, task.getTaskLeaderId());
            ps.setString(3, dateFormat.format(task.getStartDate()));
            ps.setString(4, dateFormat.format(task.getEndDate()));
            ps.setInt(5, task.getProjectId());

            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            task.setTaskId(id);

            try {
                for (int i = 0; i < task.getAssignedUserIds().length; i++) {
                    SQL = "INSERT INTO user_has_task (user_id, task_id) VALUES (?, ?)";
                    ps = con.prepareStatement(SQL);
                    ps.setInt(1, (int) Array.get(task.getAssignedUserIds(), i));
                    ps.setInt(2, task.getTaskId());
                    ps.executeUpdate();
                }
            } catch (Exception e) {
                con.rollback();
            }

            if (task.getTaskDependencyIds().length != 0) {
                try {
                    for (int i = 0; i < task.getTaskDependencyIds().length; i++) {
                        SQL = "INSERT INTO task_has_dependency (dependant_task_id, dependency_task_id) VALUES (?, ?)";
                        ps = con.prepareStatement(SQL);
                        ps.setInt(1, task.getTaskId());
                        ps.setInt(2, (int) Array.get(task.getTaskDependencyIds(), i));
                        ps.executeUpdate();
                    }
                } catch (Exception e) {
                    con.rollback();
                }
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Task> getTaskList(){
        ArrayList<Task> taskList = new ArrayList<>();
        try{

            PreparedStatement ps = con.prepareStatement(selectStatement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task task = taskMapper.mapRow(rs);
                taskList.add(task);
            }

        }catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
        return taskList;
    }

}
