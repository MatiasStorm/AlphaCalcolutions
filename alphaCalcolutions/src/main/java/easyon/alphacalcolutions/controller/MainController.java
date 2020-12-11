package easyon.alphacalcolutions.controller;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.model.exception.CreateTaskHasDependencyException;
import easyon.alphacalcolutions.model.exception.CreateUserHasTaskException;
import easyon.alphacalcolutions.service.ProjectService;
import easyon.alphacalcolutions.service.TaskService;
import easyon.alphacalcolutions.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

     private final UserService userService;
     private final ProjectService projectService;
     private final TaskService taskService;

    public MainController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/index/submit")
    public String indexSubmit(){
        return "redirect:/project";
    }

    @GetMapping("/project/create")
    public String createProject(Model model, Project project){
        model.addAttribute("project", project);
        model.addAttribute("userList", userService.getUserList());
        return "createProject";
    }

    @GetMapping("/project/edit")
    public String editProject(@RequestParam int projectId, Model model){
        model.addAttribute("project", projectService.getProject(projectId));
        model.addAttribute("userList", userService.getUserList());
        return "editProject";
    }

    @GetMapping("/project")
    public String seeProjects(Model model){
        model.addAttribute("projectList", projectService.getProjectList());
        model.addAttribute("singleProject", projectService.getProject(2));
        return "seeProjects";
    }

    @PostMapping("/project/create/submit")
    public String createProjectSubmit(Project project){
        projectService.createProject(project);
        return "redirect:/task?projectId=" + project.getProjectId();
    }

    @PostMapping("/project/edit/submit")
    public String editProjectSubmit(Project project){
        projectService.updateProject(project);
        return "redirect:/project";
    }

    @PostMapping("/project/delete")
    public String deleteProject(@RequestParam int projectId){
        projectService.deleteProject(projectId);
        return "redirect:/project";
    }

    @GetMapping("/task")
    public String seeTasks(Model model, @RequestParam int projectId){
        model.addAttribute("taskList", taskService.getTaskList(projectId));
        model.addAttribute("project", projectService.getProject(projectId));
        return "seeTasks";
    }

    @GetMapping("/task/create")
    public String createTask(Model model ,Task task, @RequestParam int projectId){
        model.addAttribute("task", task);
        model.addAttribute("userList", projectService.getAssignedUsersFromProject(projectId));
        model.addAttribute("taskList", taskService.getTaskList(projectId).getTasks());
        model.addAttribute("projectName", projectService.getProject(projectId).getTitle());
        return "createTask";
    }

    @PostMapping("/task/create/submit")
    public String createTaskSubmit(Task task, Model model){
        String errorMsg;
        try {
            taskService.createTask(task);
            return "redirect:/task?projectId=" + task.getProjectId();
        }
         catch (CreateUserHasTaskException | CreateTaskHasDependencyException e) {
            errorMsg = e.getMessage();
        }
        model.addAttribute("errorMsg", errorMsg);
        return createTask(model, task, task.getProjectId());
    }

    @GetMapping("/task/edit")
    public String editTask(Model model, @RequestParam int taskId){
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("userList", projectService.getAssignedUsersFromProject(task.getProjectId()));
        model.addAttribute("taskList", taskService.getTaskList(task.getProjectId()).getTasks());
        model.addAttribute("projectName", "InsertProjectName");
        return "editTask";
    }

    @PostMapping("/task/edit/submit")
    public String editTaskSubmit(Task task, Model model){
        String errorMsg;
        try {
            taskService.updateTask(task);
            return "redirect:/task?projectId=" + task.getProjectId();
        } catch (CreateUserHasTaskException | CreateTaskHasDependencyException e) {
            errorMsg = e.getMessage();
        }
        model.addAttribute("errorMsg", errorMsg);
        return editTask(model, task.getTaskId());
    }

    @PostMapping("/task/delete")
    public String deleteTask(int taskId, int projectId){
        taskService.deleteTask(taskId);
        return "redirect:/task?projectId=" + projectId;
    }

    @GetMapping("task/diagram")
    public String ganntDiagram(@RequestParam int projectId, Model model){
        model.addAttribute("taskList", taskService.getTaskList(projectId));
        model.addAttribute("project", projectService.getProject(projectId));
        return "taskDiagrams";
    }

    @GetMapping("/user")
    public String users(@RequestParam(required = false) String search, Model model){
        model.addAttribute("userList" , userService.getUserList());
            if(search != null){
                List<User> searchList = userService.searchUser(search);
                model.addAttribute("userList", searchList);
            }
        return "users";
    }

    @GetMapping("/user/create")
    public String createUser(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("userTitleList", userService.getUserTitleList());
        return "createUser";
    }

    @PostMapping("/user/create/submit")
    public String createUserSubmit(User user) {
        userService.createUser(user);
        return "redirect:/user";
    }

    @GetMapping("/user/edit")
    public String editUser(Model model, @RequestParam int userId){
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("userTitleList", userService.getUserTitleList());
        return "editUser";
    }

    @PostMapping("/user/edit/submit")
    public String editUserSubmit(User user){
        userService.updateUser(user);
        return "redirect:/user";
    }

    @PostMapping("/user/delete")
    public String editUserSubmit(int userId){
        userService.deleteUser(userId);
        return "redirect:/user";
    }
}

