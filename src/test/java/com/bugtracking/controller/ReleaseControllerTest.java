package com.bugtracking.controller;

import com.bugtracking.model.ReleaseRequest;
import com.bugtracking.model.ReleaseVO;
import com.bugtracking.service.IReleaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ReleaseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IReleaseService releaseService;

    @InjectMocks
    private ReleaseController releaseController;

    @Before
    public void setUp() {
        // Set up MockMvc for testing
        mockMvc = MockMvcBuilders.standaloneSetup(releaseController).build();
    }

    @Test
    public void testGetReleases() throws Exception {
        // Given: Mock the service response
        when(releaseService.findAll()).thenReturn(List.of(new ReleaseVO()));

        // When/Then: Perform GET request and expect a 200 status with specific JSON content
        mockMvc.perform(get("/api/v1/releases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    public void testGetReleaseById() throws Exception {
        // Given: Mock the service response
        int id = 1;
        when(releaseService.findById(id)).thenReturn(new ReleaseVO());

        // When/Then: Perform GET request with an ID and expect a 200 status
        mockMvc.perform(get("/api/v1/releases/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        // Given: Mock the service response
        ReleaseRequest releaseRequest = new ReleaseRequest();
        when(releaseService.save(any(ReleaseRequest.class))).thenReturn(new ReleaseVO());

        // When/Then: Perform POST request and expect a 200 status
        mockMvc.perform(post("/api/v1/releases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(releaseRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        // Given: Mock the service response
        ReleaseRequest releaseRequest = new ReleaseRequest();
        releaseRequest.setId(1);
        when(releaseService.save(any(ReleaseRequest.class))).thenReturn(new ReleaseVO());

        // When/Then: Perform PUT request and expect a 200 status
        mockMvc.perform(put("/api/v1/releases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(releaseRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        // Given: Mock the service response
        int id = 1;
        when(releaseService.delete(id)).thenReturn("Release deleted successfully");

        // When/Then: Perform DELETE request with an ID and expect a 200 status
        mockMvc.perform(delete("/api/v1/releases")
                .param("id", String.valueOf(id)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetReleaseByName() throws Exception {
        // Given: Mock the service response
        String name = "ReleaseName";
        when(releaseService.findMyReleaseByName(name)).thenReturn(new ReleaseVO());

        // When/Then: Perform GET request with a name parameter and expect a 200 status
        mockMvc.perform(get("/api/v1/releases/name")
                .param("name", name))
                .andExpect(status().isOk());
    }
}
