package com.bugtracking.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.bugtracking.entity.Release;

@DataJpaTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class ReleaseRepositoryTest {

    @Autowired
    private ReleaseRepository releaseRepository;

    @BeforeEach
    public void setUp() {
        // Clear any existing data in the repository before each test
        releaseRepository.deleteAll();
    }

    @Test
    public void testFindByName_ShouldFindExistingRelease() {
        Release release = new Release();
        release.setName("Version 1.0");
        releaseRepository.save(release);

        Optional<Release> foundRelease = releaseRepository.findByName("Version 1.0");
        assertTrue(foundRelease.isPresent());
        assertEquals("Version 1.0", foundRelease.get().getName());
    }

    @Test
    public void testFindReleaseByNameCustom_ShouldFindExistingRelease() {
        Release release = new Release();
        release.setName("Custom Version");
        releaseRepository.save(release);

        Optional<Release> foundRelease = releaseRepository.findReleaseByNameCustom("Custom Version");
        assertTrue(foundRelease.isPresent());
        assertEquals("Custom Version", foundRelease.get().getName());
    }

    @Test
    public void testFindByName_ShouldNotFindNonExistentRelease() {
        Optional<Release> foundRelease = releaseRepository.findByName("NonExistent Version");
        assertFalse(foundRelease.isPresent());
    }

    @Test
    public void testFindReleaseByNameCustom_ShouldNotFindNonExistentRelease() {
        Optional<Release> foundRelease = releaseRepository.findReleaseByNameCustom("NonExistent Custom Version");
        assertFalse(foundRelease.isPresent());
    }
}
