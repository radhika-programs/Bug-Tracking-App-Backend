package com.bugtracking.service;

import com.bugtracking.entity.Release;
import com.bugtracking.exception.ReleaseNotFoundException;
import com.bugtracking.model.ReleaseRequest;
import com.bugtracking.model.ReleaseVO;
import com.bugtracking.repository.ReleaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class ReleaseServiceImplTest {

    @Mock
    private ReleaseRepository releaseRepository;

    @InjectMocks
    private ReleaseServiceImpl releaseService;

    @Before
    public void setUp() {
        // Mock data for testing
    }

    @Test
    public void testFindAll() {
        // Mock the repository to return a list of Release entities
        List<Release> releaseList = new ArrayList<>();
        // Add Release objects to the list

        Mockito.when(releaseRepository.findAll()).thenReturn(releaseList);

        // Call the service method
        List<ReleaseVO> releaseVOList = releaseService.findAll();

        // Assertions
        assertNotNull(releaseVOList);
        // Add assertions for verifying that the Release entities are correctly mapped to ReleaseVOs
    }

    @Test
    public void testFindById() throws ReleaseNotFoundException {
        int releaseId = 1;
        // Create a mock Release entity
        Release release = new Release();
        // Set properties of the Release entity

        Mockito.when(releaseRepository.findById(releaseId)).thenReturn(Optional.of(release));

        // Call the service method
        ReleaseVO releaseVO = releaseService.findById(releaseId);

        // Assertions
        assertNotNull(releaseVO);
        // Add assertions to verify that the Release entity is correctly mapped to ReleaseVO
    }

    @Test
    public void testSave() throws ReleaseNotFoundException {
        ReleaseRequest releaseRequest = new ReleaseRequest();
        // Set properties of the ReleaseRequest

        // Create a mock Release entity
        Release release = new Release();
        // Set properties of the Release entity

        Mockito.when(releaseRepository.save(Mockito.any())).thenReturn(release);

        // Call the service method
        ReleaseVO releaseVO = releaseService.save(releaseRequest);

        // Assertions
        assertNotNull(releaseVO);
        // Add assertions to verify that the Release entity is correctly mapped to ReleaseVO
    }

    @Test
    public void testDelete() throws ReleaseNotFoundException {
        int releaseId = 1;

        // Mock the repository to perform delete operation
        Mockito.doNothing().when(releaseRepository).deleteById(releaseId);

        // Call the service method
        String response = releaseService.delete(releaseId);

        // Assertions
        assertNotNull(response);
        assertEquals("Release has been deleted", response);
    }

    @Test
    public void testFindByName() throws ReleaseNotFoundException {
        String releaseName = "ReleaseName";
        // Create a mock Release entity
        Release release = new Release();
        // Set properties of the Release entity

        Mockito.when(releaseRepository.findByName(releaseName)).thenReturn(Optional.of(release));

        // Call the service method
        ReleaseVO releaseVO = releaseService.findByName(releaseName);

        // Assertions
        assertNotNull(releaseVO);
        // Add assertions to verify that the Release entity is correctly mapped to ReleaseVO
    }

    @Test
    public void testFindMyReleaseByName() throws ReleaseNotFoundException {
        String releaseName = "ReleaseName";
        // Create a mock Release entity
        Release release = new Release();
        // Set properties of the Release entity

        Mockito.when(releaseRepository.findReleaseByNameCustom(releaseName)).thenReturn(Optional.of(release));

        // Call the service method
        ReleaseVO releaseVO = releaseService.findMyReleaseByName(releaseName);

        // Assertions
        assertNotNull(releaseVO);
        // Add assertions to verify that the Release entity is correctly mapped to ReleaseVO
    }
}
