package io.roadmaps.core.integrations.jpa.repositories;

import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.module.ModuleRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = Module.class, idClass = UUID.class)
public interface JpaModuleRepository extends ModuleRepository {

    @Override
    @Query("SELECT m FROM Module m WHERE m.id = :id")
    @Transactional
    Optional<Module> findModule(@Param("id") UUID id);

    @Override
    @Transactional
    void save(Module module);
}
