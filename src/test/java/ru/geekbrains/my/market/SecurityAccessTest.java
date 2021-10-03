package ru.geekbrains.my.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityAccessTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    public void securityBadAccessTest() throws Exception{
        mockMvc.perform(get("/api/v1/orders"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void securityGoodAccessTest() throws Exception{
        mockMvc.perform(get("/api/v1/products"))
                .andDo(print())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void tokenSecurityTest() throws Exception{
        String jsonRequest = "{\n" + "\t\"username\": \"admin\",\n" +
                "\t\"password\": \"100\"\n" + "}";
        MvcResult mvcResult = mockMvc.perform(
                post("/api/v1/auth")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String token = mvcResult.getResponse().getContentAsString();
        token = token.replace("{\"token\":\"","").replace("\"}","");

        mockMvc.perform(
                get("/api/v1/orders")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
