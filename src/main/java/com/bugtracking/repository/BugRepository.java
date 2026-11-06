package com.bugtracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bugtracking.entity.Bug;

public interface BugRepository extends JpaRepository<Bug, Integer> {
    // Find a bug by its status.
    Optional<Bug> findByStatus(String status);

    // Custom query to find a bug by status using a named parameter.
    // Example usage: bugRepository.findBugByStatusCustom("Open")
    @Query(value = "SELECT bugDetail FROM Bug bugDetail WHERE bugDetail.status = :status")
    Optional<Bug> findBugByStatusCustom(@Param("status") String status);
}
