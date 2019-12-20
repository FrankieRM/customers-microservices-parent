package com.icrid.customer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entidad Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull(message = "El nombre del cliente es un campo obligatorio")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "El apellido del cliente es un campo obligatorio")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NotNull(message = "La edad del cliente es un campo obligatorio")
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotNull(message = "La fecha de nacimiento del cliente es un campo obligatorio")
    @Column(name = "birthday", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    @Column(name = "probableDeathOfDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate probableDeathOfDate;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getProbableDeathOfDate() {
        return probableDeathOfDate;
    }

    public void setProbableDeathOfDate(LocalDate probableDeathOfDate) {
        this.probableDeathOfDate = probableDeathOfDate;
    }
}