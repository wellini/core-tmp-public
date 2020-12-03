package edu.roadmaps.core.rest.leaves;

import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.rest.dto.leaf.LeafDto;
import edu.roadmaps.core.service.leaf.LeafService;
import edu.roadmaps.core.service.leaf.LeafServiceStrategy;
import edu.roadmaps.core.service.converter.DtoConverter;
import edu.roadmaps.core.service.converter.LeafDtoConverterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LeafControllerRestFacade {
    private final LeafServiceStrategy<Leaf> serviceStrategy;
    private final LeafDtoConverterStrategy dtoConverterStrategy;

    @Autowired
    public LeafControllerRestFacade(LeafServiceStrategy<Leaf> serviceStrategy, LeafDtoConverterStrategy dtoConverterStrategy) {
        this.serviceStrategy = serviceStrategy;
        this.dtoConverterStrategy = dtoConverterStrategy;
    }

    public LeafDto create(LeafDto dto){
        LeafType receivedType = dto.getType();
        DtoConverter converter = dtoConverterStrategy.getConverter(receivedType);
        LeafService<Leaf> service = serviceStrategy.getService(receivedType);

        return converter.toFullDto(service.create(converter.toEntity(dto)));
    }
    public LeafDto get(UUID id){
        LeafService<Leaf> service = serviceStrategy.getService(LeafType.LEAF);
        Leaf found = service.get(id);
        DtoConverter converter = dtoConverterStrategy.getConverter(found.getType());
        return converter.toFullDto(found);
    }
    public void delete(UUID id){
        LeafService<Leaf> service = serviceStrategy.getService(LeafType.LEAF);
        service.delete(id);
    }
    public LeafDto update(LeafDto dto, UUID id){
        LeafType receivedType = dto.getType();
        DtoConverter converter = dtoConverterStrategy.getConverter(receivedType);
        LeafService<Leaf> service = serviceStrategy.getService(receivedType);
        Leaf leaf = service.update(converter.toEntity(dto), id);

        return converter.toFullDto(service.create(converter.toEntity(dto)));

    }
}
