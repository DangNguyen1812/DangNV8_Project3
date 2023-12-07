package com.udacity.jdnd.course3.critter.Service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Exception.ScheduleException;
import com.udacity.jdnd.course3.critter.Repository.ScheduleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get a list of schedules associated with a specific customer by their ID.
     *
     * @param customerId
     * @return A list of ScheduleDTOs containing information about the schedules
     */

    public List<Schedule> getScheduleByCustomerId(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        List<Schedule> scheduleList = new ArrayList<>();
        List<Long> petIds = customer.getPets().stream().map(Pet::getPetId).collect(Collectors.toList());
        if (customer != null && customer.getPets() != null) {
            petIds.forEach(petId -> scheduleList.addAll(getScheduleByPetId(petId)));
        }
        return scheduleList;
    }

    /**
     * Save a schedule, associating employees and pets with it.
     *
     * @param schedule
     * @return A ScheduleDTO object representing the schedule.
     * @throws ScheduleException If employees or pets are not found for the
     *                           schedule.
     */

    public Schedule save(Schedule schedule) {
        List<Long> employeeIds = schedule.getEmployees().stream().map(Employee::getEmployeeId).collect(Collectors.toList());
        List<Employee> employeeList = employeeService.getEmployeesByEmployeeIds(employeeIds);

        List<Long> petIds = schedule.getPets().stream().map(Pet::getPetId).collect(Collectors.toList());
        List<Pet> petList = petService.getPetsByPetIds(petIds);

        if (employeeList != null && petList != null) {
            schedule.setEmployees(employeeList);
            schedule.setPets(petList);
            schedule = scheduleRepository.saveAndFlush(schedule);
            schedule.setScheduleId(schedule.getScheduleId());
        } else {
            throw new ScheduleException("Not found Employee or Pet for this Schedule!");
        }
        return schedule;
    }

    /**
     * Get a list of schedules associated with a specific pet by its ID.
     *
     * @param petId The ID of the pet to filter by schedules .
     * @return A list of ScheduleDTOs containing information about the schedules
     */

    public List<Schedule> getScheduleByPetId(Long petId) {
        return scheduleRepository.findByPets(petId);
    }

    /**
     * Get a list of schedules associated with a specific employee by their ID.
     *
     * @param employeeId The ID of the employee to filter by schedules
     * @return A list of ScheduleDTOs containing information about the schedules
     */

    public List<Schedule> getScheduleByEmployeeId(Long employeeId) {
        return scheduleRepository.findByEmployees(employeeId);
    }

    /**
     * Get a list of all schedules.
     *
     * @return a list of ScheduleDTOs containing information about all schedules.
     */

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }



}
