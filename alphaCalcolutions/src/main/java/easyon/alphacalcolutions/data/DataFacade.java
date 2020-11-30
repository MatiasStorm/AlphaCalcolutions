package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Employee;
import easyon.alphacalcolutions.repository.EmployeeDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataFacade implements IDataFacade{

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public void createEmployee(Employee employee){
        employeeDAO.createEmployee(employee);
    }

    public ArrayList<Employee> getEmployeeList(){
       return employeeDAO.getEmployeeList();
    }

    public Employee getEmployee(int employeeId){
        return employeeDAO.getEmployee(employeeId);
    }

}
