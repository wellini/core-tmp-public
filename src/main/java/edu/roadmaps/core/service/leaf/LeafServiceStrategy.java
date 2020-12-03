package edu.roadmaps.core.service.leaf;

import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LeafServiceStrategy<E extends Leaf> {
    private final List<LeafService<E>> services;
    private Map<LeafType, LeafService<E>> map;

    @Autowired
    public LeafServiceStrategy(List<LeafService<E>> services) {
        this.services = services;
    }

    @PostConstruct
    private void init() {
        this.map = new HashMap<>();
        services.forEach(c -> this.map.put(c.getLeafType(), c));
    }

    public LeafService<E> getService(LeafType t) {
        return this.map.get(t);
    };
}
