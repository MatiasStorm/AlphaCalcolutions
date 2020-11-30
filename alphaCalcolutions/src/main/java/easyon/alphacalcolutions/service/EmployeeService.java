package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService {
    private final DataFacade dataFacade;

    public EmployeeService(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void createEmployee(Employee employee){
        dataFacade.createEmployee(employee);
    }

    public ArrayList<Employee> getEmployeeList(){
        return dataFacade.getEmployeeList();
    }
}
