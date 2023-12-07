package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * SQL Native Query
     *
     * @param employeeId
     * @return
     */
    @Query(value = "SELECT * FROM schedule scd WHERE scd.schedule_id IN (SELECT es.schedule_id FROM employee_schedule es WHERE es.employee_id = :employeeId)", nativeQuery = true)
    List<Schedule> findByEmployees(Long employeeId);

    /**
     * SQL Native Query
     *
     * @param petId
     * @return
     */
    @Query(value = "SELECT * FROM schedule scd WHERE scd.schedule_id IN (SELECT ps.schedule_id FROM pet_schedule ps WHERE ps.pet_id = :petId)", nativeQuery = true)
    List<Schedule> findByPets(Long petId);

}
