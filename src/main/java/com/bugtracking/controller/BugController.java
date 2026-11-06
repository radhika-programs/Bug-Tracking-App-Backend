package com.bugtracking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.bugtracking.model.BugRequest;
import com.bugtracking.model.BugVO;
import com.bugtracking.service.IBugService;

@RestController
@RequestMapping("api/v1/bugs")
public class BugController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BugController.class);

    @Autowired
    private IBugService bugService;

    // Endpoint to fetch all Bugs.
    @GetMapping
    public ResponseEntity<List<BugVO>> getBugs() {
        LOGGER.info("Fetching all bugs...");

        List<BugVO> bugVOS = null;

        try {
            // Fetch all bugs from the database
            bugVOS = bugService.findAll();

            if (CollectionUtils.isEmpty(bugVOS)) {
                LOGGER.info("No bugs found.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Error while fetching bug details.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bugVOS, HttpStatus.OK);
    }

    // Endpoint to get a bug by its ID.
    // Example URL: http://localhost:9090/api/v1/bugs/1
    @GetMapping("/{id}")
    public ResponseEntity<BugVO> getBugById(@PathVariable("id") int id) {
        LOGGER.info("Fetching bug by ID: {}", id);

        BugVO bugVO = null;
        try {
            // Fetch a bug by its ID from the database
            bugVO = bugService.findById(id);

            if (bugVO == null) {
                LOGGER.info("Bug details not found.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            LOGGER.error("Exception while fetching bug by ID", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bugVO, HttpStatus.OK);
    }

    // Endpoint to create a new bug.
    // Example URL: http://localhost:9090/api/v1/bugs
    // Sample JSON request body: {"type": "Bug Type", "applicationImpacted": "Application Impacted", ...}
    @PostMapping
    public ResponseEntity<BugVO> save(@RequestBody BugRequest bugRequest) {
        LOGGER.info("Creating a new bug...");

        if (bugRequest == null) {
            LOGGER.info("Invalid bug request.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BugVO bugVO = null;
        try {
            // Save the new bug in the database
            bugVO = bugService.save(bugRequest);

            if (bugVO == null) {
                LOGGER.info("Bug details are not saved.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            LOGGER.error("Exception while saving bug", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(bugVO, HttpStatus.OK);
    }

    // Endpoint to update an existing Bug.
    // Example URL: http://localhost:9090/api/v1/bugs
    // Sample JSON request body: {"id": 1, "type": "Updated Bug Type", "applicationImpacted": "Updated Application Impacted", ...}
    @PutMapping
    public ResponseEntity<BugVO> update(@RequestBody BugRequest bugRequest) {
        LOGGER.info("Updating an existing bug...");

        if (bugRequest == null) {
            LOGGER.info("Invalid bug request.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BugVO bugVO = null;
        try {
            // Update the existing bug in the database
            bugVO = bugService.save(bugRequest);

            if (bugVO == null) {
                LOGGER.info("Bug details are not updated.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            LOGGER.error("Exception while updating the bug", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(bugVO, HttpStatus.OK);
    }

    // Endpoint to delete an existing bug by its ID.
    // Example URL: http://localhost:9090/api/v1/bugs?id=1
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") int id) {
        LOGGER.info("Deleting bug by ID: {}", id);

        String response = null;
        try {
            // Delete the bug by its ID from the database
            response = bugService.delete(id);
        } catch (Exception ex) {
            LOGGER.error("Exception while deleting bug", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to find a Bug by its status.
    // Example URL: http://localhost:9090/api/v1/bugs/status?status=Open
    @GetMapping("/status")
    public ResponseEntity<BugVO> getBugByStatus(@RequestParam("status") String status) {
        LOGGER.info("Fetching bug by status: {}", status);

        BugVO bugVO = null;
        try {
            // Fetch a bug by its status from the database
            bugVO = bugService.findMyBugByStatus(status);

            if (bugVO == null) {
                LOGGER.info("Bug details not found.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            LOGGER.error("Exception while fetching bug by status", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bugVO, HttpStatus.OK);
    }
}
