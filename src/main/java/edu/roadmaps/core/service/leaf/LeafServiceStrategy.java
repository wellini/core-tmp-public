package edu.roadmaps.core.service.leaf;


import edu.roadmaps.core.model.entity.leaf.LeafType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LeafServiceStrategy {
    @Autowired
    private List<LeafService> services;

    private Map<LeafType, LeafService> map;

    @PostConstruct
    private void init() {
        this.map = new HashMap<>();
        services.stream().forEach(c -> this.map.put(c.getLeafType(), c));
    }

    public LeafService getService(LeafType t) {
        return this.map.get(t);
    };

}
