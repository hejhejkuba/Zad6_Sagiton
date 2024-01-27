package login.login.home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(username = "user1", password = "password1", roles = "USER")
    public void testHomeWithAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("username"))
                .andExpect(model().attribute("username", "[ROLE_USER]"));

    }
    @Test
    @WithMockUser(username = "admin", password = "adminPass", roles = "ADMIN")
    public void testHomeWithAuthenticatedAdmin() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(content().string(containsString("Admin Web Page")));
    }
    @Test
    public void testHomeWithNonAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/forbidden"));
    }
}