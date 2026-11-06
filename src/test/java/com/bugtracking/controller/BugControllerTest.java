package com.bugtracking.controller;

import com.bugtracking.model.BugRequest;
import com.bugtracking.model.BugVO;
import com.bugtracking.service.IBugService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class BugControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IBugService bugService;

    @InjectMocks
    private BugController bugController;

    @Before
    public void setUp() {
        // Set up MockMvc for testing
        mockMvc = MockMvcBuilders.standaloneSetup(bugController).build();
    }

    @Test
    public void testGetBugs() throws Exception {
        // Given
        List<BugVO> bugVOS = new ArrayList<>();
        BugVO bugVO = new BugVO();
        bugVO.setId(1);
        bugVO.setType("Bug Type");
        bugVOS.add(bugVO);

        // Mock the service response
        when(bugService.findAll()).thenReturn(bugVOS);

        // When/Then: Perform GET request and expect a 200 status with specific JSON content
        mockMvc.perform(get("/api/v1/bugs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].type").value("Bug Type"));
    }

    @Test
    public void testGetBugById() throws Exception {
        // Given
        int id = 1;
        BugVO bugVO = new BugVO();
        bugVO.setId(id);
        bugVO.setType("Bug Type");

        // Mock the service response
        when(bugService.findById(id)).thenReturn(bugVO);

        // When/Then: Perform GET request with an ID and expect a 200 status with specific JSON content
        mockMvc.perform(get("/api/v1/bugs/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.type").value("Bug Type"));
    }

    @Test
    public void testSave() throws Exception {
        // Given
        BugRequest bugRequest = new BugRequest();
        bugRequest.setType("Bug Type");

        BugVO bugVO = new BugVO();
        bugVO.setId(1);
        bugVO.setType("Bug Type");

        // Mock the service response
        when(bugService.save(any(BugRequest.class))).thenReturn(bugVO);

        // When/Then: Perform POST request and expect a 200 status with specific JSON content
        mockMvc.perform(post("/api/v1/bugs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bugRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Bug Type"));
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        BugRequest bugRequest = new BugRequest();
        bugRequest.setId(1);
        bugRequest.setType("Updated Bug Type");

        BugVO bugVO = new BugVO();
        bugVO.setId(1);
        bugVO.setType("Updated Bug Type");

        // Mock the service response
        when(bugService.save(any(BugRequest.class))).thenReturn(bugVO);

        // When/Then: Perform PUT request and expect a 200 status with updated JSON content
        mockMvc.perform(put("/api/v1/bugs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bugRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Updated Bug Type"));
    }

    @Test
    public void testDelete() throws Exception {
        // Given
        int id = 1;
        String responseMessage = "Bug deleted successfully";

        // Mock the service response
        when(bugService.delete(id)).thenReturn(responseMessage);

        // When/Then: Perform DELETE request with an ID and expect a 200 status
        mockMvc.perform(delete("/api/v1/bugs")
                .param("id", String.valueOf(id)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBugByStatus() throws Exception {
        // Given
        String status = "Open";
        BugVO bugVO = new BugVO();
        bugVO.setId(1);
        bugVO.setType("Bug Type");

        // Mock the service response
        when(bugService.findMyBugByStatus(status)).thenReturn(bugVO);

        // When/Then: Perform GET request with a status parameter and expect a 200 status with specific JSON content
        mockMvc.perform(get("/api/v1/bugs/status")
                .param("status", status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Bug Type"));
    }
}
