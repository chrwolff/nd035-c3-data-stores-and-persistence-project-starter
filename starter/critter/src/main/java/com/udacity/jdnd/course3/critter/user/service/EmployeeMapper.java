package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.EntityDTOMapper;
import com.udacity.jdnd.course3.critter.user.controller.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.model.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper extends EntityDTOMapper<EmployeeEntity, EmployeeDTO> {
    @Override
    public EmployeeDTO convertEntityToDto(EmployeeEntity employeeEntity, EmployeeDTO employeeDTO) {
        super.convertEntityToDto(employeeEntity, employeeDTO);
        employeeDTO.setDaysAvailable(employeeEntity.getDaysAvailable());
        employeeDTO.setSkills(employeeEntity.getSkills());
        return employeeDTO;
    }

    @Override
    public EmployeeEntity convertDtoToEntity(EmployeeDTO employeeDTO, EmployeeEntity employeeEntity) {
        super.convertDtoToEntity(employeeDTO, employeeEntity);
        employeeEntity.setDaysAvailable(employeeDTO.getDaysAvailable());
        employeeEntity.setSkills(employeeDTO.getSkills());
        return employeeEntity;
    }
}
