package com.bugtracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bugtracking.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    // Find an application by its name.
    Optional<Application> findByName(String name);

    // Custom query to find an application by name using a named parameter.
    // Example usage: applicationRepository.findApplicationByNameCustom("AppName")
    @Query(value = "SELECT app FROM Application app WHERE app.name = :name")
    Optional<Application> findApplicationByNameCustom(@Param("name") String name);
}

/*
 Optional<Application> findByName(String name):

This method is used to find an application by its name.
It uses Spring Data JPA's query generation feature, so you don't need to provide a custom query.
The name parameter is the name of the application you want to search for.
It returns an Optional<Application> which may or may not contain the found application.
@Query annotation:

This annotation is used to define a custom JPQL (Java Persistence Query Language) query.
Select app from Application app where name=:name is the JPQL query defined here.
Select app specifies that you want to select an Application entity.
from Application app specifies the entity you are selecting from and assigns it the alias app.
where name=:name specifies the condition for the query, where the name attribute of the Application entity matches the provided name parameter.
Optional<Application> findApplicationByNameCustom(@Param("name") String name):

This is a custom query method that uses the JPQL query defined with the @Query annotation.
It takes a name parameter, which is used to replace the :name placeholder in the JPQL query.
It returns an Optional<Application> which may or may not contain the found application.
Overall,  ApplicationRepository provides methods for finding applications by name, with both a standard Spring Data JPA method and a custom JPQL query method.
 */