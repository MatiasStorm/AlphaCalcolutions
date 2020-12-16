package easyon.alphacalcolutions.controller;

import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.service.ProjectService;
import easyon.alphacalcolutions.service.TaskService;
import easyon.alphacalcolutions.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@WebMvcTest(controllers = MainController.class)
class MainControllerTest {
    @Autowired // Dependency inject the MockMvc
    private MockMvc mockMvc;

    /* Mock all services */
    @MockBean
    private UserService userService;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private TaskService taskService;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login")));
    }

    @Test
    void indexSubmit() throws Exception {
        mockMvc.perform(post("/index/submit"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/project"));
    }

    @Test
    void createProject() throws Exception {
        ArrayList<User> userList = new ArrayList<>();
        User u = new User();
        u.setFirstName("John");
        u.setLastName("Doe");
        UserTitle t = new UserTitle();
        t.setUserTitle("Senior Developer");
        u.setTitle(t);
        userList.add(u);

        given(userService.getUserList()).willReturn(userList);

        mockMvc.perform(get("/project/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(u.getFirstName())));
    }

    @Test
    void createTaskSubmit() throws Exception {
        mockMvc.perform(post("/task/create/submit").param("projectId", "1"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/task?projectId=1"));
    }


    @Test
    void createUserSubmit() throws Exception {
        mockMvc.perform(post("/user/create/submit"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/user"));
    }
}