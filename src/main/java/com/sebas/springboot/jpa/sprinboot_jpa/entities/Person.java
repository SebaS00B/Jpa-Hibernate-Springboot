package com.sebas.springboot.jpa.sprinboot_jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;

    @Column (name = "programming_languague")
    private String programmingLanguague;
    
    public Person() {
    }
    
    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Person(Long id, String name, String lastname, String programmingLanguague) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguague = programmingLanguague;
    }
    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getProgrammingLanguague() {
        return programmingLanguague;
    }
    public void setProgrammingLanguague(String programmingLanguague) {
        this.programmingLanguague = programmingLanguague;
    }


    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLanguague="
                + programmingLanguague + "]";
    }
    

    
}
