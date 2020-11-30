package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Employee;

import java.util.ArrayList;

public interface IDataFacade {

    public void createEmployee(Employee employee);

    public ArrayList<Employee> getEmployeeList();

}
