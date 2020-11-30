package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Employee;
import easyon.alphacalcolutions.model.EmployeeTitle;

import java.util.ArrayList;

public interface IDataFacade {

    //----------------------------- EMPLOYEE -------------------------------------


    public void createEmployee(Employee employee);

    public ArrayList<Employee> getEmployeeList();

    public Employee getEmployee(int employeeId);

    //----------------------------- EMPLOYEE TITLE -------------------------------------

    public ArrayList<EmployeeTitle> getEmployeeTitleList();

}
