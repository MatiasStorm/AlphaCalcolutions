package easyon.alphacalcolutions.controller;

import easyon.alphacalcolutions.model.Employee;
import easyon.alphacalcolutions.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MainController {

     private final EmployeeService employeeService;

    public MainController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/createEmployee")
    public String createUser(Model model, Employee employee) {
        model.addAttribute("employee", employee);
        return "createEmployee";
    }

    @PostMapping("/createEmployee/submit")
    public String createUserSubmit(Employee employee) {

       employeeService.createEmployee(employee);

        return "redirect:/index";
    }
}
