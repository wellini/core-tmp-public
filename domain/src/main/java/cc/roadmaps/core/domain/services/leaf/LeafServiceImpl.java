package cc.roadmaps.core.domain.services.leaf;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.model.leaf.Leaf;
import cc.roadmaps.core.domain.model.leaf.LeafRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class LeafServiceImpl implements LeafService {

    private final LeafRepository repository;

    @Override
    public Leaf getLeaf(UUID id) {
        return repository.findLeaf(id).orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(Leaf.class, id));
    }
}
