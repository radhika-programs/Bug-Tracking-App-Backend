package com.bugtracking.service;

import java.util.List;

import com.bugtracking.exception.ApplicationNotFoundException;
import com.bugtracking.model.ApplicationRequest;
import com.bugtracking.model.ApplicationVO;

public interface IApplicationService {
    // Fetch all applications.
    List<ApplicationVO> findAll();

    // Find an application by its ID.
    ApplicationVO findById(int id) throws ApplicationNotFoundException;

    // Create or update an application.
    ApplicationVO save(ApplicationRequest applicationRequest) throws ApplicationNotFoundException;

    // Delete an application by its ID.
    String delete(int id) throws ApplicationNotFoundException;

    // Find an application by its name.
    ApplicationVO findByName(String name) throws ApplicationNotFoundException;

    // Find an application by name using a custom method.
    ApplicationVO findMyApplicationByName(String name) throws ApplicationNotFoundException;
}
