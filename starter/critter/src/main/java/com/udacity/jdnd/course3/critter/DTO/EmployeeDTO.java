package com.udacity.jdnd.course3.critter.DTO;

import com.udacity.jdnd.course3.critter.Enum.EmployeeSkill;

import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not
 * map to the database directly.
 */
public class EmployeeDTO {
    private long employeeId;
    private String employeeName;
    private Set<EmployeeSkill> employeeSkills;
    private Set<DayOfWeek> employeeDaysAvailable;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Set<EmployeeSkill> getEmployeeSkills() {
        return employeeSkills;
    }

    public void setEmployeeSkills(Set<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }

    public Set<DayOfWeek> getEmployeeDaysAvailable() {
        return employeeDaysAvailable;
    }

    public void setEmployeeDaysAvailable(Set<DayOfWeek> employeeDaysAvailable) {
        this.employeeDaysAvailable = employeeDaysAvailable;
    }
}
