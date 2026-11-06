package com.bugtracking.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bugtracking.model.ApplicationRequest;
import com.bugtracking.model.ApplicationVO;
import com.bugtracking.service.IApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IApplicationService applicationService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        // Set up MockMvc for testing
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetApplications() throws Exception {
        // Given
        List<ApplicationVO> applicationVOS = new ArrayList<>();
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setId(1);
        applicationVO.setName("MS Office");
        applicationVOS.add(applicationVO);

        // Mock the service response
        when(applicationService.findAll()).thenReturn(applicationVOS);

        // When/Then: Perform GET request and expect a 200 status
        mockMvc.perform(get("/api/v1/applications"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetApplicationById() throws Exception {
        // Given
        int id = 1;
        ApplicationVO applicationVO = new ApplicationVO();

        // Mock the service response
        when(applicationService.findById(id)).thenReturn(applicationVO);

        // When/Then: Perform GET request with an ID and expect a 200 status
        mockMvc.perform(get("/api/v1/applications/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateApplication() throws Exception {
        // Given
        ApplicationRequest applicationRequest = new ApplicationRequest();
        ApplicationVO applicationVO = new ApplicationVO();

        // Mock the service response
        when(applicationService.save(any(ApplicationRequest.class))).thenReturn(applicationVO);

        // When/Then: Perform POST request and expect a 200 status
        mockMvc.perform(post("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(applicationRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateApplication() throws Exception {
        // Given
        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setId(1);
        applicationRequest.setName("Paint - Adobe");

        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setId(applicationRequest.getId());
        applicationVO.setName(applicationRequest.getName());

        // Mock the service response
        when(applicationService.save(any(ApplicationRequest.class))).thenReturn(applicationVO);

        // When/Then: Perform PUT request and expect a 200 status with updated content

        mockMvc.perform(put("/api/v1/applications") // Provide the ID in the path
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(applicationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(applicationRequest.getId()))
                .andExpect(jsonPath("$.name").value(applicationRequest.getName()));
        /*
         * These lines use jsonPath to assert specific values in the JSON response body.
			jsonPath("$.id").value(applicationRequest.getId()) checks that the response body has a field named "id" with a value equal to the ID of the applicationRequest.
			jsonPath("$.name").value(applicationRequest.getName()) checks that the response body has a field named "name" with a value equal to the name of the applicationRequest.
         */
    }

    @Test
    public void testDeleteApplication() throws Exception {
        // Given
        int id = 1;
        String responseMessage = "Application deleted successfully";

        // Mock the service response
        when(applicationService.delete(id)).thenReturn(responseMessage);

        // When/Then: Perform DELETE request with an ID and expect a 200 status
        mockMvc.perform(delete("/api/v1/applications")
                .param("id", String.valueOf(id)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetApplicationByName() throws Exception {
        // Given
        String name = "Test Application";
        ApplicationVO applicationVO = new ApplicationVO();

        // Mock the service response
        when(applicationService.findMyApplicationByName(name)).thenReturn(applicationVO);

        // When/Then: Perform GET request with a name parameter and expect a 200 status
        mockMvc.perform(get("/api/v1/applications/name")
                .param("name", name))
                .andExpect(status().isOk());
    }
}
