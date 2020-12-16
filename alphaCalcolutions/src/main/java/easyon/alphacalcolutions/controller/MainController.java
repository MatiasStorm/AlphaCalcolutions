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
import org.springframework.web.context.request.WebRequest;

import java.nio.file.WatchEvent;
import java.util.HashMap;
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
    public String indexSubmit(WebRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);
        if(user == null){
            model.addAttribute("loginError", true);
            return "index";
        }
        setSessionInfo(request, user);
        return "redirect:/project";
    }

    @GetMapping("/logout")
    public String logout(WebRequest request){
        setSessionInfo(request, null);
        return "redirect:/";
    }

    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }

//    private HashMap loginCheck(WebRequest request){
//        HashMap log
//    }


    @GetMapping("/project/create")
    public String createProject(Model model, Project project, WebRequest request){
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        model.addAttribute("project", project);
        model.addAttribute("userList", userService.getUserList());
        model.addAttribute("isAdmin", loggedInUser.getAdmin());

        return "createProject";
    }

    @GetMapping("/project/edit")
    public String editProject(@RequestParam int projectId, Model model, WebRequest request){
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        model.addAttribute("project", projectService.getProject(projectId));
        model.addAttribute("userList", userService.getUserList());
        model.addAttribute("isAdmin", loggedInUser.getAdmin());

        return "editProject";
    }

    @GetMapping("/project")
    public String seeProjects(Model model, WebRequest request){
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        model.addAttribute("projectList", projectService.getProjectList());
        model.addAttribute("singleProject", projectService.getProject(2));
        model.addAttribute("isAdmin", loggedInUser.getAdmin());
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
    public String seeTasks(Model model, @RequestParam int projectId, WebRequest request){
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        model.addAttribute("taskList", taskService.getTaskList(projectId));
        model.addAttribute("project", projectService.getProject(projectId));
        model.addAttribute("isAdmin", loggedInUser.getAdmin());

        return "seeTasks";
    }

    @GetMapping("/task/create")
    public String createTask(Model model ,Task task, @RequestParam int projectId, WebRequest request){
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        model.addAttribute("task", task);
        model.addAttribute("userList", projectService.getAssignedUsersFromProject(projectId));
        model.addAttribute("taskList", taskService.getTaskList(projectId).getTasks());
        model.addAttribute("projectName", projectService.getProject(projectId).getTitle());
        model.addAttribute("isAdmin", loggedInUser.getAdmin());

        return "createTask";
    }

    @PostMapping("/task/create/submit")
    public String createTaskSubmit(Task task, Model model, WebRequest request){
        String errorMsg;
        try {
            taskService.createTask(task);
            return "redirect:/task?projectId=" + task.getProjectId();
        }
         catch (CreateUserHasTaskException | CreateTaskHasDependencyException e) {
            errorMsg = e.getMessage();
        }
        model.addAttribute("errorMsg", errorMsg);
        return createTask(model, task, task.getProjectId(),request);
    }

    @GetMapping("/task/edit")
    public String editTask(Model model, @RequestParam int taskId, WebRequest request){
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("userList", projectService.getAssignedUsersFromProject(task.getProjectId()));
        model.addAttribute("taskList", taskService.getTaskList(task.getProjectId()).getTasks());
        model.addAttribute("projectName", "InsertProjectName");
        model.addAttribute("isAdmin", loggedInUser.getAdmin());

        return "editTask";
    }

    @PostMapping("/task/edit/submit")
    public String editTaskSubmit(Task task, Model model, WebRequest request){
        String errorMsg;
        try {
            taskService.updateTask(task);
            return "redirect:/task?projectId=" + task.getProjectId();
        } catch (CreateUserHasTaskException | CreateTaskHasDependencyException e) {
            errorMsg = e.getMessage();
        }
        model.addAttribute("errorMsg", errorMsg);
        return editTask(model, task.getTaskId(), request);
    }

    @PostMapping("/task/delete")
    public String deleteTask(int taskId, int projectId){
        taskService.deleteTask(taskId);
        return "redirect:/task?projectId=" + projectId;
    }

    @GetMapping("task/diagram")
    public String displayDiagrams(@RequestParam int projectId, Model model, WebRequest request){
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        model.addAttribute("taskList", taskService.getTaskList(projectId));
        model.addAttribute("project", projectService.getProject(projectId));
        model.addAttribute("isAdmin", loggedInUser.getAdmin());

        return "taskDiagrams";
    }

    @GetMapping("/user")
    public String searchUsers(@RequestParam(required = false) String search, Model model, WebRequest request){
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        model.addAttribute("userList" , userService.getUserList());
            if(search != null){
                List<User> searchList = userService.searchUser(search);
                model.addAttribute("userList", searchList);
            }
        model.addAttribute("isAdmin", loggedInUser.getAdmin());

        return "users";
    }

    @GetMapping("/user/create")
    public String createUser(Model model, User user, WebRequest request) {
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("userTitleList", userService.getUserTitleList());
        model.addAttribute("isAdmin", loggedInUser.getAdmin());

        return "createUser";
    }

    @PostMapping("/user/create/submit")
    public String createUserSubmit(User user) {
        userService.createUser(user);
        return "redirect:/user";
    }

    @GetMapping("/user/edit")
    public String editUser(Model model, @RequestParam int userId, WebRequest request){
        User loggedInUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (loggedInUser == null) { // If your aren't logged in, redirect to index.html
            return "redirect:/";
        }
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("userTitleList", userService.getUserTitleList());
        model.addAttribute("isAdmin", loggedInUser.getAdmin());

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

