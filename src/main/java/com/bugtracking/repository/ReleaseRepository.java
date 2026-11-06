package com.bugtracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bugtracking.entity.Release;

public interface ReleaseRepository extends JpaRepository<Release, Integer> {
    // Find a release by its name.
    Optional<Release> findByName(String name);

    // Custom query to find a release by name using a named parameter.
    // Example usage: releaseRepository.findReleaseByNameCustom("ReleaseName")
    @Query(value = "SELECT rel FROM Release rel WHERE rel.name = :name")
    Optional<Release> findReleaseByNameCustom(@Param("name") String name);
}
