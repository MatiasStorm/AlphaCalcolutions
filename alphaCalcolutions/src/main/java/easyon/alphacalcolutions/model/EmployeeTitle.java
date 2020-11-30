package easyon.alphacalcolutions.model;

public class EmployeeTitle {
    private int employeeTitleId;
    private String employeeTitle;

    public EmployeeTitle(int employeeTitleId, String employeeTitle) {
        this.employeeTitleId = employeeTitleId;
        this.employeeTitle = employeeTitle;
    }

    public EmployeeTitle() {
    }

    public int getEmployeeTitleId() {
        return employeeTitleId;
    }

    public void setEmployeeTitleId(int employeeTitleId) {
        this.employeeTitleId = employeeTitleId;
    }

    public String getEmployeeTitle() {
        return employeeTitle;
    }

    public void setEmployeeTitle(String employeeTitle) {
        this.employeeTitle = employeeTitle;
    }
}
