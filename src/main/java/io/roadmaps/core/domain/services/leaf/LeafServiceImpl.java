package io.roadmaps.core.domain.services.leaf;

import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LeafServiceImpl implements LeafService {

    private final LeafRepository repository;

    @Override
    public Leaf getLeaf(Long id) {
        return repository.findLeaf(id).orElseThrow(EntityNotFoundException::new);
    }
}
