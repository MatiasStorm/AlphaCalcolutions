package easyon.alphacalcolutions.model;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private int hourlySalary;
    private UserTitle title;
    private String username;
    private String password;
    private Boolean isAdmin;


    @Override
    public int hashCode() {
        return userId;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User u = (User) o;
        return userId == u.getUserId()
                && firstName.equals(u.getFirstName())
                && lastName.equals(u.getLastName())
                && hourlySalary == u.getHourlySalary()
                && title.equals(u.getTitle())
                && username.equals(u.getUsername())
                && password.equals(u.getPassword())
                && isAdmin == u.getAdmin();
    }

    public UserTitle getTitle() {
        return title;
    }

    public void setTitle(UserTitle title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
