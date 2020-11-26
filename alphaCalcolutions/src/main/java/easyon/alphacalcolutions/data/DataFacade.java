package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Employee;
import easyon.alphacalcolutions.repository.EmployeeDAO;
import org.springframework.stereotype.Component;

@Component
public class DataFacade implements IDataFacade{

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public void createEmployee(Employee employee){
        employeeDAO.createEmployee(employee);
    }

}
