package easyon.alphacalcolutions.mapper;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class TaskMapper {

    public Task mapRow(ResultSet rs) throws SQLException, ParseException {

        Task task = new Task();
        task.setTaskId(rs.getInt("task_id"));
        task.setTitle(rs.getString("task_title"));
        task.setTaskLeaderId(rs.getInt("task_leader_id"));
        task.setStartDate(rs.getString("task_start_date"));
        task.setEndDate(rs.getString("task_end_date"));
        task.setTaskId(rs.getInt("project_id"));
        task.setAssignedUserIds(rs.getString("assigned_user_ids").split(","));
        task.setTaskDependencyIds(rs.getString("task_dependency_ids").split(","));
        return task;

    }
}
