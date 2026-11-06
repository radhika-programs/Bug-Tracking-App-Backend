package com.bugtracking.service;

import java.util.List;

import com.bugtracking.exception.BugNotFoundException;
import com.bugtracking.model.BugRequest;
import com.bugtracking.model.BugVO;

public interface IBugService {
    // Fetch all bugs.
    List<BugVO> findAll();

    // Find a bug by its ID.
    BugVO findById(int id) throws BugNotFoundException;

    // Create or update a bug.
    BugVO save(BugRequest bugRequest) throws BugNotFoundException;

    // Delete a bug by its ID.
    String delete(int id) throws BugNotFoundException;

    // Find a bug by its status.
    BugVO findByStatus(String status) throws BugNotFoundException;

    // Find a bug by status using a custom method.
    BugVO findMyBugByStatus(String status) throws BugNotFoundException;
}
