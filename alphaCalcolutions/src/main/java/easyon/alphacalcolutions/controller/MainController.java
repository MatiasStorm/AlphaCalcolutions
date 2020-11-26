package easyon.alphacalcolutions.controller;

import easyon.alphacalcolutions.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MainController {

    @GetMapping("/createEmployee")
    public String createUser(Model model, Employee employee) {
        model.addAttribute("employee", employee);
        return "createEmployee";
    }

    @PostMapping("/createEmployee/submit")
    public String createUserSubmit(Employee employee, Model model, WebRequest request) {

        Employee newEmployee = EmployeeService.createEmployee(employee);

        return "redirect:/index";
    }
}
