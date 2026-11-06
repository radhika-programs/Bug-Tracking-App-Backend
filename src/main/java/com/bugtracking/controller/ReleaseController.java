package com.bugtracking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bugtracking.model.ReleaseRequest;
import com.bugtracking.model.ReleaseVO;
import com.bugtracking.service.IReleaseService;

@RestController
@RequestMapping("api/v1/releases")
public class ReleaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReleaseController.class);

    @Autowired
    private IReleaseService releaseService;

    // Endpoint to fetch all Releases.
    @GetMapping
    public ResponseEntity<List<ReleaseVO>> getReleases() {
        LOGGER.info("Fetching all releases...");

        List<ReleaseVO> releaseVOS = null;

        try {
            // Fetch all releases from the database
            releaseVOS = releaseService.findAll();
            LOGGER.info("Release response :{}", releaseVOS);

            if (CollectionUtils.isEmpty(releaseVOS)) {
                LOGGER.info("No releases found !!!");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Error while fetching release details.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(releaseVOS, HttpStatus.OK);
    }

    // Endpoint to get a release by its ID.
    // Example URL: http://localhost:9090/api/v1/releases/1
    @GetMapping("/{id}")
    public ResponseEntity<ReleaseVO> getReleaseById(@PathVariable("id") int id) {
        LOGGER.info("Fetching release by ID: {}", id);

        ReleaseVO releaseVO = null;
        try {
            // Fetch a release by its ID from the database
            releaseVO = releaseService.findById(id);

            if (releaseVO == null) {
                LOGGER.info("Release details not found.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            LOGGER.error("Exception while fetching release by ID", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(releaseVO, HttpStatus.OK);
    }

    // Endpoint to create a new release.
    // Example URL: http://localhost:9090/api/v1/releases
    // Sample JSON request body: {"name": "Release Name", "releaseDate": "2023-09-09", ...}
    @PostMapping
    public ResponseEntity<ReleaseVO> save(@RequestBody ReleaseRequest releaseRequest) {
        LOGGER.info("Creating a new release...");

        if (releaseRequest == null) {
            LOGGER.info("Invalid release request.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReleaseVO releaseVO = null;
        try {
            // Save the new release in the database
            releaseVO = releaseService.save(releaseRequest);

            if (releaseVO == null) {
                LOGGER.info("Release details are not saved.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            LOGGER.error("Exception while saving release", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(releaseVO, HttpStatus.OK);
    }

    // Endpoint to update an existing Release.
    // Example URL: http://localhost:9090/api/v1/releases
    // Sample JSON request body: {"id": 1, "name": "Updated Release Name", "releaseDate": "2023-09-09", ...}
    @PutMapping
    public ResponseEntity<ReleaseVO> update(@RequestBody ReleaseRequest releaseRequest) {
        LOGGER.info("Updating an existing release...");

        if (releaseRequest == null) {
            LOGGER.info("Invalid release request.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReleaseVO releaseVO = null;
        try {
            // Update the existing release in the database
            releaseVO = releaseService.save(releaseRequest);

            if (releaseVO == null) {
                LOGGER.info("Release details are not updated.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            LOGGER.error("Exception while updating the release", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(releaseVO, HttpStatus.OK);
    }

    // Endpoint to delete an existing release by its ID.
    // Example URL: http://localhost:9090/api/v1/releases?id=1
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") int id) {
        LOGGER.info("Deleting release by ID: {}", id);

        String response = null;
        try {
            // Delete the release by its ID from the database
            response = releaseService.delete(id);
        } catch (Exception ex) {
            LOGGER.error("Exception while deleting release", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to find a Release by its name.
    // Example URL: http://localhost:9090/api/v1/releases/name?name=ReleaseName
    @GetMapping("/name")
    public ResponseEntity<ReleaseVO> getReleaseByName(@RequestParam("name") String name) {
        LOGGER.info("Fetching release by name: {}", name);

        ReleaseVO releaseVO = null;
        try {
            // Fetch a release by its name from the database
            releaseVO = releaseService.findMyReleaseByName(name);

            if (releaseVO == null) {
                LOGGER.info("Release details not found.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            LOGGER.error("Exception while fetching release by name", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(releaseVO, HttpStatus.OK);
    }
}
