package edu.roadmaps.core.service.leaf;

import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.model.exception.NotFoundException;
import edu.roadmaps.core.repository.LeafBaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class LeafServiceImpl implements LeafService{

    private final LeafBaseRepository<Leaf> repository;
    private final ModelMapper modelMapper;

    @Autowired
    public LeafServiceImpl(LeafBaseRepository<Leaf> repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Leaf create(Leaf leaf) {
        leaf.setId(UUID.randomUUID());
        return save(leaf);
    }

    @Transactional
    public Leaf save(Leaf leaf) {
        return repository.save(leaf);
    }

    @Override
    @Transactional
    public Leaf update(Leaf leaf, UUID id) {
        Optional<Leaf> found = repository.findById(id);
        Leaf updatingLeaf = found.orElseThrow(() -> new NotFoundException("leaf not found"));
        modelMapper.map(leaf, updatingLeaf);

        return repository.save(updatingLeaf);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public Leaf get(UUID uuid) {
        return getLeaf(uuid);
    }

    protected Leaf getLeaf(UUID uuid) {
        Optional<Leaf> found = repository.findById(uuid);
        return found.orElseThrow(() -> new NotFoundException("leaf not found"));
    }

    @Override
    public LeafType getLeafType() {
        return LeafType.LEAF;
    }
}
