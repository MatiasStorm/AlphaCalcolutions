/* Matias Storm */
package easyon.alphacalcolutions.data.repository;

import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.model.UserTitle;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest extends AbstractDAOTest{
    private UserDAO userDAO = new UserDAO(con);

    private User getExpectedUser(){
        User u = new User();
        u.setFirstName("John");
        u.setLastName("Doe");
        u.setHourlySalary(200);
        u.setUsername("UserName");
        u.setPassword("passowrd");
        u.setAdmin(false);
        UserTitle t = new UserTitle();
        t.setUserTitleId(1);
        t.setUserTitle("Computer Science");
        u.setTitle(t);
        return u;
    }

    @Test
    void createUser() {
        User expectedUser = getExpectedUser();
        User actualUser = userDAO.createUser(expectedUser);
        assertNotNull(actualUser);
        assertEquals(expectedUser.getAdmin(), actualUser.getAdmin());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getTitle().getUserTitleId(), actualUser.getTitle().getUserTitleId());
    }

    @Test
    void getUserList() {
        List<User> users = userDAO.getUserList();
        assertEquals(4, users.size());
    }

    @Test
    void getUser() {
        User actualUser = userDAO.getUserById(1);
        assertNotNull(actualUser);
        assertEquals(1, actualUser.getUserId());
    }

    @Test
    void getUserTitleList() {
        List<UserTitle> titles = userDAO.getUserTitleList();
        assertEquals(2, titles.size());
    }

    @Test
    void getUsersByIds(){
        int[] ids = {1,3,4};
        List<User> users = userDAO.getUsersByIds(ids);
        assertEquals(ids.length, users.size());
        for(int i = 0; i < users.size(); i++){
            assertEquals(ids[i], users.get(i).getUserId());
        }
    }
    
    @Test
    void updateUser(){
        User expectedUser = getExpectedUser();
        expectedUser.setUserId(1);
        userDAO.updateUser(expectedUser);
        User actualUser = userDAO.getUserById(expectedUser.getUserId());
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void deleteUser() {
        userDAO.deleteUser(1);
        User u = userDAO.getUserById(1);
        assertNull(u);
    }

    @Test
    void getUserSearchSingleUser() {
        List<User> users = userDAO.getUserSearch("John");
        assertEquals(1, users.size());
        assertEquals("John", users.get(0).getFirstName());
    }

    @Test
    void getUserSearchMoreUsers() {
        String searchPhrase = "Doe";
        List<User> users = userDAO.getUserSearch(searchPhrase);
        assertEquals(2, users.size());
        for(User u : users){
            assertEquals(searchPhrase, u.getLastName());
        }
    }

    @Test
    void loginValid(){
        User u = userDAO.login("username", "password");
        assertNotNull(u);
        assertEquals("username", u.getUsername());
        assertEquals("password", u.getPassword());
    }

    @Test
    void loginInvalidCase(){
        User u = userDAO.login("Username", "Password");
        assertNull(u);
    }

    @Test
    void loginInvalidUsername(){
        User u = userDAO.login("wrontUsername", "password");
        assertNull(u);
    }

    @Test
    void loginInvalidPassword(){
        User u = userDAO.login("username", "wrongPassword");
        assertNull(u);
    }
}
