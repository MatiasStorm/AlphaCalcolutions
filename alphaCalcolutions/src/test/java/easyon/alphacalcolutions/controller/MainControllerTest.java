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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;

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


    private MockHttpSession getRegularSession(){
        MockHttpSession session = new MockHttpSession();
        User u = new User();
        u.setAdmin(false);
        session.setAttribute("user", u);
        return session;
    }

    private MockHttpSession getAdminSession(){
        MockHttpSession session = new MockHttpSession();
        User u = new User();
        u.setAdmin(true);
        session.setAttribute("user", u);
        return session;
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login")));
    }

    @Test
    void indexSubmitNoLogin() throws Exception {
        mockMvc.perform(post("/index/submit"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Username eller Password er forkert")));
    }

    @Test
    void indexSubmitWithLogin() throws Exception {
        String username = "username";
        String password = "password";
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        body.add("password", password);
        given(userService.login(username, password)).willReturn(new User());
        mockMvc.perform(post("/index/submit")
                        .params(body))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/project"));
    }

    @Test
    void createProjectUnauthorized() throws Exception {
        mockMvc.perform(get("/project/create"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void createProjectRegularUser() throws Exception {
        mockMvc.perform(get("/project/create").session(getRegularSession()))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void createProjectAdminUser() throws Exception {
        ArrayList<User> userList = new ArrayList<>();
        User u = new User();
        u.setFirstName("John");
        u.setLastName("Doe");
        UserTitle t = new UserTitle();
        t.setUserTitle("Senior Developer");
        u.setTitle(t);
        userList.add(u);

        given(userService.getUserList()).willReturn(userList);

        mockMvc.perform(get("/project/create").session(getAdminSession()))
                .andExpect(status().is(200));
    }

    @Test
    void createTaskSubmit() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/task/create/submit")
                .param("projectId", "1")
                .session(getRegularSession());
        mockMvc.perform(request)
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/task?projectId=1"));
    }

    @Test
    void createUserSubmit() throws Exception {
        mockMvc.perform(post("/user/create/submit").session(getAdminSession()))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    void allEndpointAccessibility() throws Exception {
        String[] getEndpoints = { "/project", "/project/create", "/project/edit?projectId=1",
                                "/task?projectId=1", "/task/create?projectId=1", "/task/edit?projectId=1&taskId=1",
                                "/user", "/user/create", "/user/edit?userId=1" };
        String[] postEndpoints = {"/task/create/submit","/task/edit/submit","/project/edit/submit",
                                  "/project/create/submit", "/user/create/submit", "/user/edit/submit",
                                  "/project/delete?projectId=1", "/user/delete?userId=1", "/task/delete?taskId=1&projectId=1"};
        for (String endpoint : getEndpoints){
            mockMvc.perform(get(endpoint))
                    .andExpect(status().is(302))
                    .andExpect(redirectedUrl("/"));
        }
        for (String endpoint : postEndpoints){
            mockMvc.perform(post(endpoint))
                    .andExpect(status().is(302))
                    .andExpect(redirectedUrl("/"));
        }
    }
}