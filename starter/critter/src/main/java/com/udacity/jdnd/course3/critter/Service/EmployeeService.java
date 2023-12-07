package com.udacity.jdnd.course3.critter.Service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Enum.EmployeeSkill;
import com.udacity.jdnd.course3.critter.Exception.EmployeeException;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get an employee by their unique ID.
     *
     * @param id The ID of the employee
     * @return The EmployeeDTO containing information about the employee.
     * @throws EmployeeException If no employee with the corresponding ID is found.
     */

    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new EmployeeException("Not found Employee with id:" + id);
        }
    }

    /**
     * Save Employee
     */

    public Employee save(Employee employeeDto) {
        Employee e = modelMapper.map(employeeDto, Employee.class);
        return employeeRepository.saveAndFlush(e);
    }


    public Long saveEmployeeDaysAvailable(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Optional<Employee> e = employeeRepository.findById(employeeId);
        if (e.isPresent()) {
            Employee employee = e.get();
            employee.setEmployeeDaysAvailable(daysAvailable);
            employeeRepository.save(employee);
        } else {
            throw new EmployeeException("Not found Employee with id:" + employeeId);
        }
        return employeeId;
    }

    /**
     * Get a list of employees with specific skills available on a given day of
     * the week.
     *
     * @param skills    The set of skills to filter employees by.
     * @param dayOfWeek The specific day of the week when employees should be
     *                  available.
     * @return A list of EmployeeDTOs containing information about employees who
     * meet the criteria.
     */

    public List<Employee> getEmployeesBySkillsAndDaysAvailable(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        return employeeRepository.findAllByEmployeeDaysAvailableAndEmployeeSkillsIn(dayOfWeek, skills);
    }

    /**
     * Get a list of employees by their unique employee IDs.
     *
     * @param employeeIds The list of unique employee IDs
     * @return A list of EmployeeDTOs containing information about the selected
     * employees.
     */

    public List<Employee> getEmployeesByEmployeeIds(List<Long> employeeIds) {
        return employeeRepository.findByEmployeeIdIn(employeeIds);
    }

}