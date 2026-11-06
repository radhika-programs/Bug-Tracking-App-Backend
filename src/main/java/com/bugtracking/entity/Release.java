package com.bugtracking.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "RELEASE_TBL")
public class Release implements Serializable {

    private static final long serialVersionUID = -3993446790046416495L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "release_gen")
    @SequenceGenerator(name = "release_gen", sequenceName = "RELEASE_TBL_SEQ", allocationSize = 1)
    @Column(name = "RELEASE_ID")
    private int id;

    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    private String status;
    private String comments;

    // Uncomment the following code if you want to establish a one-to-many relationship with the Bug entity.
    // @OneToMany(mappedBy = "release")
    // private Set<Bug> bugs;
}
/*
 Import statements are used to include necessary classes from external libraries.

@Data annotation is from Lombok and is used to generate boilerplate code like getters, setters, and toString methods.

The @Entity annotation marks this class as a JPA entity, indicating that it should be persisted in the database.

The @Table annotation specifies the name of the database table to which this entity is mapped.

The @Id annotation denotes the primary key of the entity.

The @GeneratedValue annotation with GenerationType.SEQUENCE and @SequenceGenerator is used to generate unique IDs for the id field.

@Column annotations are used to specify column names in the database.

@Temporal(TemporalType.DATE) is used to specify the type of temporal data for the releaseDate field.

The remaining fields represent attributes of the Release entity.

There's a commented-out section for the bugs field. If you want to establish a one-to-many relationship with the Bug entity, you can uncomment this code
 */