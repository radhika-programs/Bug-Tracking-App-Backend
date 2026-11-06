package com.bugtracking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import com.bugtracking.entity.Application;
import com.bugtracking.exception.ApplicationNotFoundException;
import com.bugtracking.model.ApplicationRequest;
import com.bugtracking.model.ApplicationVO;
import com.bugtracking.repository.ApplicationRepository;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class ApplicationServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationServiceImplTest.class);

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Before
    public void setUp() {
        // Initialize some test data if needed
    }

    // Test case for finding all applications
    @Test
    public void testFindAll() {
        LOGGER.info("Running testFindAll");

        // Prepare a list of applications
        Application app1 = new Application();
        app1.setId(1);
        app1.setName("BARD");
        app1.setDescription("BARD Application");
        app1.setOwner("John Doe");

        Application app2 = new Application();
        app2.setId(2);
        app2.setName("CHATGPT");
        app2.setDescription("CHATGPT Application");
        app2.setOwner("Jane Smith");

        when(applicationRepository.findAll()).thenReturn(List.of(app1, app2));

        // Test the findAll method
        List<ApplicationVO> applicationVOs = applicationService.findAll();

        assertNotNull(applicationVOs);
        assertEquals(2, applicationVOs.size());

        ApplicationVO applicationVO1 = applicationVOs.get(0);
        assertEquals(app1.getId(), applicationVO1.getId());
        assertEquals(app1.getName(), applicationVO1.getName());
        assertEquals(app1.getDescription(), applicationVO1.getDescription());
        assertEquals(app1.getOwner(), applicationVO1.getOwner());

        ApplicationVO applicationVO2 = applicationVOs.get(1);
        assertEquals(app2.getId(), applicationVO2.getId());
        assertEquals(app2.getName(), applicationVO2.getName());
        assertEquals(app2.getDescription(), applicationVO2.getDescription());
        assertEquals(app2.getOwner(), applicationVO2.getOwner());
    }

    // Test case for finding an application by ID
    @Test
    public void testFindById() throws ApplicationNotFoundException {
        LOGGER.info("Running testFindById");

        // Prepare an application with ID 1
        Application app = new Application();
        app.setId(1);
        app.setName("BARD");
        app.setDescription("BARD Application");
        app.setOwner("John Doe");

        when(applicationRepository.findById(1)).thenReturn(Optional.of(app));

        // Test the findById method
        ApplicationVO applicationVO = applicationService.findById(1);

        assertNotNull(applicationVO);
        assertEquals(app.getId(), applicationVO.getId());
        assertEquals(app.getName(), applicationVO.getName());
        assertEquals(app.getDescription(), applicationVO.getDescription());
        assertEquals(app.getOwner(), applicationVO.getOwner());
    }

    // Test case for handling the scenario when an application is not found by ID
    @Test(expected = ApplicationNotFoundException.class)
    public void testFindById_NotFound() throws ApplicationNotFoundException {
        LOGGER.info("Running testFindById_NotFound");

        // Configure the repository mock to return an empty optional
        when(applicationRepository.findById(999)).thenReturn(Optional.empty());

        // This should throw an ApplicationNotFoundException
        applicationService.findById(999);
    }

    // Test case for saving a new application
    @Test
    public void testSave() throws ApplicationNotFoundException {
        LOGGER.info("Running testSave");

        // Prepare a new application request
        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setName("FIOX");
        applicationRequest.setDescription("FIOX Application");
        applicationRequest.setOwner("Rahul Kumar");

        // Prepare a saved application
        Application savedApplication = new Application();
        savedApplication.setId(3); // New ID after save
        savedApplication.setName(applicationRequest.getName());
        savedApplication.setDescription(applicationRequest.getDescription());
        savedApplication.setOwner(applicationRequest.getOwner());

        // Configure the repository mock to return the saved application with the correct ID
        when(applicationRepository.save(any(Application.class)))
                .thenAnswer(invocation -> {
                    Application savedApp = invocation.getArgument(0);
                    savedApp.setId(3); // Set the expected ID
                    return savedApp;
                });

        // Test the save operation
        ApplicationVO applicationVO = applicationService.save(applicationRequest);

        assertNotNull(applicationVO);
        assertEquals(savedApplication.getId(), applicationVO.getId());
        assertEquals(savedApplication.getName(), applicationVO.getName());
        assertEquals(savedApplication.getDescription(), applicationVO.getDescription());
        assertEquals(savedApplication.getOwner(), applicationVO.getOwner());
    }

    // Test case for deleting an application
    @Test
    public void testDelete() throws ApplicationNotFoundException {
        LOGGER.info("Running testDelete");

        // Test the delete operation
        String result = applicationService.delete(1);

        assertEquals("Application has been deleted", result);

        // Verify that the deleteById method was called
        verify(applicationRepository).deleteById(1);
    }
}

/*
Explanation
//Configure the repository mock to return the saved application with the correct ID
when(applicationRepository.save(any(Application.class)))
     .thenAnswer(invocation -> {
         // Step 1: Retrieve the Application object passed as an argument to the save method
         Application savedApp = invocation.getArgument(0);

         // Step 2: Set the expected ID for the saved Application object
         savedApp.setId(3); // Simulating the assignment of a unique identifier (ID)

         // Step 3: Return the modified Application object
         // This is the object that will be returned when the save method is called on the mock repository
         return savedApp;
     });
*/