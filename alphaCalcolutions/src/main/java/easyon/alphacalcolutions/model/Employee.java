package easyon.alphacalcolutions.model;

public class Employee {

    private String firstName;
    private String lastName;
    private int titleId;
    private int hourlySalary;

    public Employee (String firstName, String lastName, int titleId, int hourlySalary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.titleId = titleId;
        this.hourlySalary = hourlySalary;
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
