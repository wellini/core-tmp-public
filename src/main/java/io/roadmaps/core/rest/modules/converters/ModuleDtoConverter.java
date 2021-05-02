package io.roadmaps.core.rest.modules.converters;

import io.roadmaps.core.model.entity.Module;
import io.roadmaps.core.rest.modules.dto.CreateModuleDto;
import io.roadmaps.core.rest.modules.dto.UpdateModuleDto;
import io.roadmaps.core.rest.modules.dto.templates.ModuleResponseDtoTemplate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModuleDtoConverter {

    private final ModelMapper modelMapper;

    public Module toDomain(CreateModuleDto dto) {
        return modelMapper.map(dto, Module.class);
    }

    public <T extends ModuleResponseDtoTemplate> T fromDomain(Module module, Class<T> clazz) {
        return modelMapper.map(module, clazz);
    }

    public void update(Module module, UpdateModuleDto dto) {
        modelMapper.map(dto, module);
    }
}
