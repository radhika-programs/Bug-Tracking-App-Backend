package com.bugtracking.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "BUG_TBL")
public class Bug implements Serializable {

    private static final long serialVersionUID = 3382528179548773084L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bug_gen")
    @SequenceGenerator(name = "bug_gen", sequenceName = "BUG_TBL_SEQ", allocationSize = 1)
    @Column(name = "BUG_ID")
    private int id;

    @Column(name = "BUG_TYPE")
    private String type;

    @Column(name = "APPLICATION_IMPACTED")
    private String applicationImpacted;

    @Column(name = "ASSIGNED_TO")
    private String assignedTo;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "CREATED_BY")
    private String createdBy;

    private String status;
    private String description;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_ID")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "RELEASE_ID")
    private Release release;
}
/*
 Import statements are used to include necessary classes from external libraries.

@Data annotation is from Lombok and is used to generate boilerplate code like getters, setters, and toString methods.

The @Entity annotation marks this class as a JPA entity, indicating that it should be persisted in the database.

The @Table annotation specifies the name of the database table to which this entity is mapped.

The @Id annotation denotes the primary key of the entity.

The @GeneratedValue annotation with GenerationType.SEQUENCE and @SequenceGenerator is used to generate unique IDs for the id field.

@Column annotations are used to specify column names in the database.

@Temporal(TemporalType.DATE) is used to specify the type of temporal data for the createdOn field.

The remaining fields represent attributes of the Bug entity.

@ManyToOne annotations are used to establish many-to-one relationships with the Application and Release entities using the JoinColumn annotation to specify the foreign key columns.
 */