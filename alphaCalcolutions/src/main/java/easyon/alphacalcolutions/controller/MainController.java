package easyon.alphacalcolutions.controller;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.service.ProjectService;
import easyon.alphacalcolutions.service.TaskService;
import easyon.alphacalcolutions.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        Project p = projectService.getProject(projectId);
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
        return "redirect:/seeProjects";
    }

    @PostMapping("/project/edit/submit")
    public String editProjectSubmit(Project project){
        projectService.createProject(project);
        return "redirect:/seeProjects";
    }

    @GetMapping("/seeTasks")
    public String seeTasks(Model model, Task task, @RequestParam int projectId){
        model.addAttribute("taskList", taskService.getTaskList(projectId));
        model.addAttribute("task", task);
        return "seeTasks";
    }

    @GetMapping("/createTask")
    public String createTask(Model model ,Task task, @RequestParam int projectId){
        model.addAttribute("task", task);
        model.addAttribute("userList", projectService.getAssignedUsersFromProject(projectId));
        model.addAttribute("projectList", projectService.getProjectList());
        model.addAttribute("projectName", "InsertProjectName");
        return "createTask";
    }

    @PostMapping("/createTask/submit")
    public String createTaskSubmit(Task task){
        String[] dependencies = new String[]{"1", "6", "11"};
        task.setTaskDependencyIds(dependencies);
        taskService.createTask(task);
        return "redirect:/seeTasks?projectId=" + task.getProjectId();
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("userList" , userService.getUserList());
//        model.addAttribute("singleUser", userService.getUser(1));
        return "users";
    }

    @GetMapping("/createUser")
    public String createUser(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("userTitleList", userService.getUserTitleList());
        return "createUser";
    }

    @PostMapping("/createUser/submit")
    public String createUserSubmit(User user) {

       userService.createUser(user);

        return "redirect:/users";
    }


    @GetMapping("/test")
    public String tes(Model model){
        model.addAttribute("users" , userService.getUserList());
        return "testSelectMultiple";
    }

    @PostMapping("/testPost")
    public String testPost(String[] users){
        return "redirect:/test";
    }
}
