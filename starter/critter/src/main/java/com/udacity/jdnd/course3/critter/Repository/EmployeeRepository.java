package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Enum.EmployeeSkill;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * JPA Query Methods
     *
     * @param employeeIds
     * @return
     */
    List<Employee> findByEmployeeIdIn(List<Long> employeeIds);

    /**
     * JPA Query Methods
     *
     * @param dayAvailable
     * @param skills
     * @return
     */
    List<Employee> findAllByEmployeeDaysAvailableAndEmployeeSkillsIn(DayOfWeek dayAvailable, Set<EmployeeSkill> skills);


}
