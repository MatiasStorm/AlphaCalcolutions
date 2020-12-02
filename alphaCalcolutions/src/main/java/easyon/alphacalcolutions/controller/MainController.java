package easyon.alphacalcolutions.controller;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.service.ProjectService;
import easyon.alphacalcolutions.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

     private final UserService userService;
     private final ProjectService projectService;


    public MainController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @PostMapping("/loginForm/submit")
    public String loginFormSubmit(){
        return "redirect:/userPage";
    }

    @GetMapping("/userPage")
    public String userPage(){
        return "userPage";
    }

    @GetMapping("/createProject")
    public String createProject(Model model, Project project){
        model.addAttribute("project", project);
        model.addAttribute("userList", userService.getUserList());

        return "createProject";
    }

    @GetMapping("/seeProjects")
    public String seeProjects(){
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

    @GetMapping("/employees")
    public String employees(Model model){
        model.addAttribute("userList" , userService.getUserList());
        model.addAttribute("singleUser", userService.getUser(1));
        return "employees";
    }

    @GetMapping("/createEmployee")
    public String createUser(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("userTitleList", userService.getUserTitleList());
        return "createEmployee";
    }

    @PostMapping("/createEmployee/submit")
    public String createUserSubmit(User user) {

       userService.createUser(user);

        return "redirect:/employees";
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
