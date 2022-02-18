package com.example.moviles.model.movil;

import com.example.moviles.model.ObjectMother.Movil;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MovilMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovilFromDto(MovilDTO dto, @MappingTarget Movil entity);
}
