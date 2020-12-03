package edu.roadmaps.core.service.leaf;

import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.model.exception.NotFoundException;
import edu.roadmaps.core.repository.LeafBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public abstract class LeafBaseServiceImpl<E extends Leaf>
        implements LeafService<E> {
    protected final LeafBaseRepository<E> repository;
    protected final ModelMapper modelMapper;

    public LeafBaseServiceImpl(LeafBaseRepository<E> repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public E create(E leaf) {
        leaf.setId(UUID.randomUUID());
        return save(leaf);
    }

    @Transactional
    public E save(E leaf) {
        return repository.save(leaf);
    }

    @Override
    @Transactional
    public E update(E leaf, UUID id) {
        Optional<E> found = repository.findById(id);
        E updatingLeaf = found.orElseThrow(() -> new NotFoundException("leaf not found"));
        modelMapper.map(leaf, updatingLeaf);

        return repository.save(updatingLeaf);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public E get(UUID uuid) {
        return getLeaf(uuid);
    }

    protected E getLeaf(UUID uuid) {
        Optional<E> found = repository.findById(uuid);
        return found.orElseThrow(() -> new NotFoundException("leaf not found"));
    }

    @Override
    public abstract LeafType getLeafType();
}
