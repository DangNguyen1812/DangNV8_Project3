package com.udacity.jdnd.course3.critter.DTO;

import com.udacity.jdnd.course3.critter.Enum.PetType;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map to
 * the database directly.
 */
public class PetDTO {
    private long petId;
    private PetType petType;
    private String petName;
    private long ownerId;
    private LocalDate petBirthDate;
    private String petNotes;

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

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
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
}
