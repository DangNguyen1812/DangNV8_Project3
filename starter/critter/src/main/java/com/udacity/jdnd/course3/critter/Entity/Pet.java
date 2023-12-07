package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.Enum.PetType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petId;

    @Enumerated(EnumType.STRING)
    @Column
    private PetType petType;

    @Column(nullable = false)
    private String petName;

    @Column
    private LocalDate petBirthDate;

    @Column
    private String petNotes;

    @ManyToMany(mappedBy = "pets", targetEntity = Schedule.class)
    private List<Schedule> schedules;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public LocalDate getPetBirthDate() {
        return petBirthDate;
    }

    public void setPetBirthDate(LocalDate petBirthDate) {
        this.petBirthDate = petBirthDate;
    }

    public String getPetNotes() {
        return petNotes;
    }

    public void setPetNotes(String petNotes) {
        this.petNotes = petNotes;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
