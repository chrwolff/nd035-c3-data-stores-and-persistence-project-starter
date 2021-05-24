package com.udacity.jdnd.course3.critter.user.controller;

import com.udacity.jdnd.course3.critter.user.model.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.model.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.service.CustomerMapper;
import com.udacity.jdnd.course3.critter.user.service.CustomerService;
import com.udacity.jdnd.course3.critter.user.service.EmployeeMapper;
import com.udacity.jdnd.course3.critter.user.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final CustomerMapper customerMapper;
    private final EmployeeMapper employeeMapper;

    UserController(CustomerService customerService, EmployeeService employeeService, CustomerMapper customerMapper, EmployeeMapper employeeMapper) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.customerMapper = customerMapper;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerEntity entity = customerMapper.convertDtoToEntity(customerDTO, new CustomerEntity());
        entity = this.customerService.save(entity);
        return customerMapper.convertEntityToDto(entity, new CustomerDTO());
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return this.customerService.getAll().stream()
            .map(customerEntity -> customerMapper.convertEntityToDto(customerEntity, new CustomerDTO()))
            .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        CustomerEntity entity = this.customerService.getByPetId(petId);
        return customerMapper.convertEntityToDto(entity, new CustomerDTO());
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity entity = employeeMapper.convertDtoToEntity(employeeDTO, new EmployeeEntity());
        entity = this.employeeService.save(entity);
        return employeeMapper.convertEntityToDto(entity, new EmployeeDTO());
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        EmployeeEntity entity = this.employeeService.get(employeeId);
        return employeeMapper.convertEntityToDto(entity, new EmployeeDTO());
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        EmployeeEntity employeeEntity = this.employeeService.get(employeeId);
        employeeEntity.setDaysAvailable(daysAvailable);
        this.employeeService.save(employeeEntity);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        return this.employeeService.getByWeekdayAndSkills(dayOfWeek, employeeDTO.getSkills())
            .stream()
            .map(employeeEntity -> employeeMapper.convertEntityToDto(employeeEntity, new EmployeeDTO()))
            .collect(Collectors.toList());
    }
}
