package com.udacity.jdnd.course3.critter.Api;

import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Utils.ConverterUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.udacity.jdnd.course3.critter.Service.ScheduleService;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private static final Logger log = LogManager.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ConverterUtils dtoService;

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        log.info("Get all schedules");

        try {
            List<Schedule> allSchedules = scheduleService.getAllSchedules();
            if (allSchedules == null) {
                log.warn("No schedules found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("Return all schedules");
            List<ScheduleDTO> petsDto = dtoService.convertToScheduleDTOList(allSchedules);
            return ResponseEntity.ok(petsDto);
        } catch (Exception ex) {
            log.error("Error when getting all customers", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<ScheduleDTO>> getScheduleForPet(@PathVariable long petId) {
        log.info("Gt schedules for pet with ID: " + petId);

        try {
            List<Schedule> scheduleByPetId = scheduleService.getScheduleByPetId(petId);
            if (scheduleByPetId == null) {
                log.warn("Not found schedules for pet id: " + petId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("Return schedules for pet id: " + petId);

            List<ScheduleDTO> petsDto = dtoService.convertToScheduleDTOList(scheduleByPetId);
            return ResponseEntity.ok(petsDto);
        } catch (Exception ex) {
            log.error("Error when getting all customers", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        log.info("Create a new schedule");

        try {
            Schedule createdSchedule = scheduleService.save(dtoService.convertToEntity(scheduleDTO));
            log.info("Schedule created: " + createdSchedule);
            return ResponseEntity.ok(dtoService.convertToDTO(createdSchedule));
        } catch (Exception ex) {
            log.error("Error when getting all customers", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ScheduleDTO>> getScheduleForEmployee(@PathVariable long employeeId) {
        log.info("Request to get schedules for employee with ID: " + employeeId);

        try {
            List<Schedule> scheduleByEmployeeId = scheduleService.getScheduleByEmployeeId(employeeId);
            if (scheduleByEmployeeId == null) {
                log.warn("Not found schedules for employee with id: " + employeeId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("Return schedule for employee with id: " + employeeId);
            List<ScheduleDTO> petsDto = dtoService.convertToScheduleDTOList(scheduleByEmployeeId);
            return ResponseEntity.ok(petsDto);
        } catch (Exception e) {
            log.error("Error when getting all customers", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ScheduleDTO>> getScheduleForCustomer(@PathVariable long customerId) {
        log.info("Get schedules for customer with id: " + customerId);

        try {
            List<Schedule> scheduleByCustomerId = scheduleService.getScheduleByCustomerId(customerId);
            if (scheduleByCustomerId == null) {
                log.warn("Not found schedule for customer id: " + customerId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("Return schedules for customer id: " + customerId);
            List<ScheduleDTO> petsDto = dtoService.convertToScheduleDTOList(scheduleByCustomerId);
            return ResponseEntity.ok(petsDto);
        } catch (Exception ex) {
            log.error("Error when getting all customers", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
