package easyon.alphacalcolutions.model;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private int titleId;
    private int hourlySalary;
    ////////// Test på at joine //////////////
    private String title;


    public Employee(int employeeId, String firstName, String lastName, int titleId, int hourlySalary, String title) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.titleId = titleId;
        this.hourlySalary = hourlySalary;
        this.title = title;
    }

    public Employee() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }
}
