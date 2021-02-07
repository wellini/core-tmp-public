package edu.roadmaps.core.rest.leaves.converter;

import edu.roadmaps.core.model.entity.LeafType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LeafDtoConverterStrategy {
    private final List<DtoConverter> converters;
    private Map<LeafType, DtoConverter> map;

    @Autowired
    public LeafDtoConverterStrategy(List<DtoConverter> converters) {
        this.converters = converters;
    }

    @PostConstruct
    private void init() {
        this.map = new HashMap<>();
        converters.forEach(c -> this.map.put(c.getLeafType(), c));
    }

    public DtoConverter getConverter(LeafType t) {
        return this.map.get(t);
    };
}
