package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.mapper.TaskMapper;
import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;

import java.lang.reflect.Array;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskDAO {
    private TaskMapper taskMapper = new TaskMapper();
    private final Connection con;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");

    public TaskDAO(Connection con) {
        this.con = con;
    }

    private String getSelectStatement(String where){
        String selectStatement = "SELECT task.*, sub_task.dependency_task_ids, GROUP_CONCAT(uht.user_id SEPARATOR ',') AS assigned_user_ids FROM task"
                                + " JOIN (SELECT task.task_id, GROUP_CONCAT(thd.dependency_task_id SEPARATOR ',') AS dependency_task_ids FROM task"
                                + " LEFT JOIN task_has_dependency thd ON thd.dependant_task_id = task.task_id"
                                + " GROUP BY task.task_id) sub_task ON task.task_id = sub_task.task_id"
                                + " LEFT JOIN user_has_task uht ON task.task_id = uht.task_id ";
        String groupBy = " GROUP BY task.task_id";
        return selectStatement + where + groupBy;
    }

    public Task createTask(Task task) {

        try {
            con.setAutoCommit(false);
            String SQL = "INSERT INTO task (task_title, task_leader_id, task_start_date, task_end_date, project_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, task.getTitle());
            ps.setInt(2, task.getTaskLeaderId());
            ps.setString(3, formatter.format(task.getStartDate()));
            ps.setString(4, formatter.format(task.getEndDate()));
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
        return task;
    }

    public ArrayList<Task> getTaskList(int projectId){
        ArrayList<Task> taskList = new ArrayList<>();
        try{

            String selectStatment = getSelectStatement(" WHERE task.project_id=?");
            PreparedStatement ps = con.prepareStatement(selectStatment);
            ps.setInt(1, projectId);
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


    public Task getTaskById(int taskId){
        try{
            String SQL = getSelectStatement(" WHERE task.task_id=?");
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, taskId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Task task = taskMapper.mapRow(rs);
                return task;
            }
        } catch (SQLException | ParseException ex){
            ex.printStackTrace();
        }
        return null;
    }


}
