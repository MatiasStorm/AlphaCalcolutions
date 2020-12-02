package easyon.alphacalcolutions.controller;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.service.ProjectService;
import easyon.alphacalcolutions.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

     private final UserService userService;
     private final ProjectService projectService;


    public MainController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
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
        model.addAttribute("singleProject", projectService.getProject(1));
        return "seeProjects";
    }

    @PostMapping("/createProject/submit")
    public String createProjectSubmit(Project project){
        projectService.createProject(project);
        return "redirect:/seeProjects";
    }

    @GetMapping("/createTask")
    public String createTask(){
        return "createTask";
    }

    @PostMapping("/createTask/submit")
    public String createTaskSubmit(){
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
