package easyon.alphacalcolutions.controller;

import easyon.alphacalcolutions.model.Employee;
import easyon.alphacalcolutions.service.EmployeeService;
import easyon.alphacalcolutions.service.EmployeeTitleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MainController {

     private final EmployeeService employeeService;
     private final EmployeeTitleService employeeTitleService;

    public MainController(EmployeeService employeeService, EmployeeTitleService employeeTitleService) {
        this.employeeService = employeeService;
        this.employeeTitleService = employeeTitleService;
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
    public String createProject(){
        return "createProject";
    }

    @GetMapping("/seeProjects")
    public String seeProjects(){
        return "seeProjects";
    }

    @PostMapping("/createProject/submit")
    public String createProjectSubmit(){

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
        model.addAttribute("employeeList" ,employeeService.getEmployeeList());
        model.addAttribute("singleEmployee", employeeService.getEmployee(1));
        return "employees";
    }

    @GetMapping("/createEmployee")
    public String createUser(Model model, Employee employee) {
        model.addAttribute("employee", employee);
        model.addAttribute("employeeTitleList", employeeTitleService.getEmployeeTitleList());
        return "createEmployee";
    }

    @PostMapping("/createEmployee/submit")
    public String createUserSubmit(Employee employee) {

       employeeService.createEmployee(employee);

        return "redirect:/employees";
    }
}
