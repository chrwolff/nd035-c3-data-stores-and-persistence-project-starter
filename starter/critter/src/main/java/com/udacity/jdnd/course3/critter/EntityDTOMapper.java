package com.udacity.jdnd.course3.critter;

import org.springframework.beans.BeanUtils;

public class EntityDTOMapper<ENTITY, DTO> {
    public DTO convertEntityToDto(ENTITY entity, DTO dto) {
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public ENTITY convertDtoToEntity(DTO dto, ENTITY entity) {
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
