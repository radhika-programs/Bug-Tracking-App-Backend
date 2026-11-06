package com.bugtracking.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.bugtracking.entity.Bug;

@DataJpaTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class BugRepositoryTest {

    @Autowired
    private BugRepository bugRepository;

    @BeforeEach
    public void setUp() {
        // Clear any existing data in the repository before each test
        bugRepository.deleteAll();
    }

    @Test
    public void testFindByStatus() {
        Bug bug = new Bug();
        bug.setStatus("Open");
        bugRepository.save(bug);

        Optional<Bug> foundBug = bugRepository.findByStatus("Open");
        assertTrue(foundBug.isPresent());
        assertEquals("Open", foundBug.get().getStatus());
    }

    @Test
    public void testFindBugByStatusCustom() {
        Bug bug = new Bug();
        bug.setStatus("In Progress");
        bugRepository.save(bug);

        Optional<Bug> foundBug = bugRepository.findBugByStatusCustom("In Progress");
        assertTrue(foundBug.isPresent());
        assertEquals("In Progress", foundBug.get().getStatus());
    }

    @Test
    public void testFindByStatus_NotFound() {
        Optional<Bug> foundBug = bugRepository.findByStatus("Non-Existent Status");
        assertFalse(foundBug.isPresent());
    }

    @Test
    public void testFindBugByStatusCustom_NotFound() {
        Optional<Bug> foundBug = bugRepository.findBugByStatusCustom("Non-Existent Status");
        assertFalse(foundBug.isPresent());
    }
}
