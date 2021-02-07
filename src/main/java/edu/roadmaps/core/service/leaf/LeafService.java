package edu.roadmaps.core.service.leaf;

import edu.roadmaps.core.exception.EntityNotFoundException;
import edu.roadmaps.core.model.entity.Leaf;
import edu.roadmaps.core.repository.LeafRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeafService {

    private final LeafRepository repository;

    public Leaf create(Leaf leaf) {
        return repository.save(leaf);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public Leaf get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No lecture with id=" + id));
    }

    public Leaf update(Leaf leaf) {
        return repository.save(leaf);
    }
}
