package com.bugtracking.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.bugtracking.entity.Application;

@DataJpaTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindApplicationByNameCustom() {
        // Create and save an application
        Application application = new Application();
        application.setName("Adobe Photo App");
        application.setDescription("Editing tool for photos");
        application.setOwner("Priya Sharma");
        entityManager.persist(application);

        // Test the custom query method
        Optional<Application> foundApplication = applicationRepository.findApplicationByNameCustom("Adobe Photo App");
        assertTrue(foundApplication.isPresent());
        assertEquals("Adobe Photo App", foundApplication.get().getName());
    }

    @Test
    public void testFindByName() {
        // Create and save an application with more meaningful data
        Application application = new Application();
        application.setName("ImageEdit Pro");
        application.setDescription("Professional photo editing software with advanced features");
        application.setOwner("Aarav Patel");
        entityManager.persist(application);

        // Test the find by name method
        Optional<Application> foundApplication = applicationRepository.findByName("ImageEdit Pro");
        assertTrue(foundApplication.isPresent());
        assertEquals("ImageEdit Pro", foundApplication.get().getName());
        assertEquals("Professional photo editing software with advanced features", foundApplication.get().getDescription());
        assertEquals("Aarav Patel", foundApplication.get().getOwner());
    }

    // Add more tests as needed for your specific repository

}
