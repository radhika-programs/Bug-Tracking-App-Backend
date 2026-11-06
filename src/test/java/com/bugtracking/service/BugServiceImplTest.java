package com.bugtracking.service;

import com.bugtracking.entity.Application;
import com.bugtracking.entity.Bug;
import com.bugtracking.entity.Release;
import com.bugtracking.exception.BugNotFoundException;
import com.bugtracking.model.BugRequest;
import com.bugtracking.model.BugVO;
import com.bugtracking.repository.BugRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class BugServiceImplTest {

    @Mock
    private BugRepository bugRepository;

    @InjectMocks
    private BugServiceImpl bugService;

    private Bug mockBug;
    private Application mockApplication;
    private Release mockRelease;

    @Before
    public void setUp() {
        // Creating mock entities for testing
        mockRelease = new Release();
        mockRelease.setId(1);
        mockRelease.setName("Release 1.0");

        mockApplication = new Application();
        mockApplication.setId(1);
        mockApplication.setName("MyApp");

        mockBug = new Bug();
        mockBug.setId(1);
        mockBug.setRelease(mockRelease);
        mockBug.setStatus("Open");
        mockBug.setType("Functional");
        mockBug.setApplication(mockApplication);
        mockBug.setDescription("Test Bug");
    }

    @Test
    public void testFindAll() {
        // Setting up a mockBug for testing
        Bug mockBug = new Bug();
        mockBug.setId(1);
        mockBug.setStatus("Open");

        List<Bug> bugs = new ArrayList<>();
        bugs.add(mockBug);

        // Configuring the bugRepository mock to return the list of bugs
        when(bugRepository.findAll()).thenReturn(bugs);

        // Calling the findAll method on bugService
        List<BugVO> bugVOS = bugService.findAll();

        // Verifying that the bugRepository.findAll() method was called once
        verify(bugRepository, times(1)).findAll();

        // Verifying that the bugVOS list has one element
        assertEquals(1, bugVOS.size());

        // Verifying that the values of the first element match the expected values
        assertEquals("Open", bugVOS.get(0).getStatus());
    }

    @Test
    public void testFindById() throws BugNotFoundException {
        // Configuring the bugRepository mock to return an Optional containing mockBug when findById is called with argument 1
        when(bugRepository.findById(1)).thenReturn(Optional.of(mockBug));

        // Calling the findById method on bugService with the specified ID
        BugVO bugVO = bugService.findById(1);

        // Verifying that the bugRepository.findById(1) method was called once
        verify(bugRepository, times(1)).findById(1);

        // Verifying that the values of the returned bugVO match the expected values
        assertEquals("Release 1.0", bugVO.getRelease().getName());
        assertEquals("Open", bugVO.getStatus());
    }

    @Test(expected = BugNotFoundException.class)
    public void testFindByIdBugNotFound() throws BugNotFoundException {
        // Configuring the bugRepository mock to return an empty Optional when findById is called with argument 2
        when(bugRepository.findById(2)).thenReturn(Optional.empty());

        // Expecting BugNotFoundException when calling findById with ID 2
        bugService.findById(2);
    }

    @Test
    public void testSave() throws BugNotFoundException {
        // Creating mock Release and Application for testing
        Release mockRelease = new Release();
        mockRelease.setId(2);
        mockRelease.setName("Release 2.0");

        Application mockApplication = new Application();
        mockApplication.setId(2);
        mockApplication.setName("AnotherApp");

        // Creating a BugRequest for testing
        BugRequest bugRequest = new BugRequest();
        bugRequest.setStatus("Open");
        bugRequest.setType("Functional");
        bugRequest.setDescription("New Bug");
        bugRequest.setRelease(mockRelease);
        bugRequest.setApplication(mockApplication);

        // Creating a mockBug with the necessary configuration
        Bug mockBug = new Bug();
        mockBug.setId(1);
        mockBug.setStatus("Open");

        // Configuring the bugRepository mock to return the mockBug when save is called
        when(bugRepository.save(any(Bug.class))).thenReturn(mockBug);

        // Calling the save method on bugService
        BugVO bugVO = bugService.save(bugRequest);

        // Verifying that the bugRepository.save() method was called once with any Bug object
        verify(bugRepository, times(1)).save(any(Bug.class));

        // Verifying that the values of the returned bugVO match the expected values
        assertEquals("Open", bugVO.getStatus());
    }

    @Test(expected = BugNotFoundException.class)
    public void testSaveInvalidRequest() throws BugNotFoundException {
        // Expecting BugNotFoundException when calling save with a null BugRequest
        bugService.save(null);
    }

    @Test
    public void testDelete() throws BugNotFoundException {
        // Calling the delete method on bugService with ID 1
        String result = bugService.delete(1);

        // Verifying that the result matches the expected value
        assertEquals("Bug has been deleted", result);

        // Verifying that the bugRepository.deleteById(1) method was called once
        verify(bugRepository, times(1)).deleteById(1);
    }

    @Test(expected = BugNotFoundException.class)
    public void testDeleteInvalidId() throws BugNotFoundException {
        // Expecting BugNotFoundException when calling delete with an invalid ID (-1)
        bugService.delete(-1);
    }

    @Test
    public void testFindByStatus() throws BugNotFoundException {
        // Mocking a Bug object
        Bug mockBug = new Bug();
        Release release = new Release();
        release.setName("Release 1.0");
        mockBug.setRelease(release);
        mockBug.setStatus("Open");

        // Configuring the bugRepository mock to return an Optional containing mockBug when findByStatus is called with "Open"
        when(bugRepository.findByStatus("Open")).thenReturn(Optional.of(mockBug));

        // Calling the findByStatus method on bugService with status "Open"
        BugVO bugVO = bugService.findByStatus("Open");

        // Verifying that the values of the returned bugVO match the expected values
        assertEquals("Release 1.0", bugVO.getRelease().getName());
        assertEquals("Open", bugVO.getStatus());

        // Verifying that the bugRepository.findByStatus("Open") method was called once
        verify(bugRepository, times(1)).findByStatus("Open");
    }

    @Test(expected = BugNotFoundException.class)
    public void testFindByStatusInvalidStatus() throws BugNotFoundException {
        // Expecting BugNotFoundException when calling findByStatus with a null status
        bugService.findByStatus(null);
    }
}
