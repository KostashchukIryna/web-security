package edu.lab.security;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
  @author   kosta
  @project   security
  @class  AccessTests
  @version  1.0.0 
  @since 30.04.2025 - 19.49
*/
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply(springSecurity())
            .build();
    }

    @Test
    @WithAnonymousUser
    public void whenAnonymThenStatusUnautorized() throws Exception {

        mockMvc.perform(get("/api/v1/books"))
            .andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello-admin"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello-user"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserAccessesUserHelloThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello-user"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperAdminAccessesStatsThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/view/stats"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserTriesToAccessStatsThenForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/books/view/stats"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void whenAdminAccessesDashboardThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/view/dashboard"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "stranger", roles = {"GUEST"})
    void whenStrangerAccessesHelloStrangerThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/stranger"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserAccessesProfileThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/view/profile"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void whenAdminAccessesUnknownThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello-unknown"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperadminAccessesProfileThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/view/profile"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "dualrole", roles = {"USER", "ADMIN"})
    void whenUserWithMultipleRolesAccessesUnknownThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello-unknown"))
            .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void whenAnonymousAccessesStrangerThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/stranger"))
            .andExpect(status().isOk());
    }
}
