package edu.roadmaps.core.service.leaf;

import edu.roadmaps.core.model.entity.leaf.Leaf;
import edu.roadmaps.core.model.entity.leaf.LeafType;
import edu.roadmaps.core.model.entity.leaf.LinkLectureLeaf;
import edu.roadmaps.core.model.exception.NotFoundException;
import edu.roadmaps.core.repository.LinkLectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class LinkLectureServiceImpl implements LeafService{

    private final LinkLectureRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public LinkLectureServiceImpl(LinkLectureRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Leaf create(Leaf leaf) {
        leaf.setId(UUID.randomUUID());
        return save((LinkLectureLeaf) leaf);
    }

    @Transactional
    public Leaf save(LinkLectureLeaf leaf) {
        return repository.save(leaf);
    }

    @Override
    @Transactional
    public Leaf update(Leaf leaf, UUID id) {
        Optional<LinkLectureLeaf> found = repository.findById(id);
        LinkLectureLeaf updatingLeaf = found.orElseThrow(() -> new NotFoundException("leaf not found"));
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
        Optional<LinkLectureLeaf> found = repository.findById(uuid);
        return found.orElseThrow(() -> new NotFoundException("leaf not found"));
    }

    @Override
    public LeafType getLeafType() {
        return LeafType.LINK_LECTURE;
    }
}
