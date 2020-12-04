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
        return "redirect:/seeProjects";
    }

    @GetMapping("/createProject")
    public String createProject(Model model, Project project){
        model.addAttribute("project", project);
        model.addAttribute("userList", userService.getUserList());
        return "createProject";
    }

    @GetMapping("/seeProjects")
    public String seeProjects(Model model){
        model.addAttribute("projectList", projectService.getProjectList());
        model.addAttribute("singleProject", projectService.getProject(2));
        return "seeProjects";
    }

    @PostMapping("/createProject/submit")
    public String createProjectSubmit(Project project){
        projectService.createProject(project);
        return "redirect:/seeProjects";
    }

    @GetMapping("/seeTasks")
    public String seeTasks(Model model, Task task){
        model.addAttribute("taskList", taskService.getTaskList());
        model.addAttribute("usersOnTaskList", taskService.getAssignedUsersFromTask(8));
        model.addAttribute("task", task);
        return "seeTasks";
    }

    @GetMapping("/createTask")
    public String createTask(Model model ,Task task){
        model.addAttribute("task", task);
        model.addAttribute("userList", userService.getUserList());
        model.addAttribute("projectList", projectService.getProjectList());
        return "createTask";
    }

    @PostMapping("/createTask/submit")
    public String createTaskSubmit(Task task){
        String[] dependencies = new String[]{"1", "6", "11"};
        task.setTaskDependencyIds(dependencies);
        taskService.createTask(task);
        return "redirect:/createTask";
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
