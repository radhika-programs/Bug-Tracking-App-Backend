package com.bugtracking.service;

import java.util.List;

import com.bugtracking.exception.ReleaseNotFoundException;
import com.bugtracking.model.ReleaseRequest;
import com.bugtracking.model.ReleaseVO;

public interface IReleaseService {
    // Fetch all releases.
    List<ReleaseVO> findAll();

    // Find a release by its ID.
    ReleaseVO findById(int id) throws ReleaseNotFoundException;

    // Create or update a release.
    ReleaseVO save(ReleaseRequest releaseRequest) throws ReleaseNotFoundException;

    // Delete a release by its ID.
    String delete(int id) throws ReleaseNotFoundException;

    // Find a release by its name.
    ReleaseVO findByName(String name) throws ReleaseNotFoundException;

    // Find a release by name using a custom method.
    ReleaseVO findMyReleaseByName(String name) throws ReleaseNotFoundException;
}
