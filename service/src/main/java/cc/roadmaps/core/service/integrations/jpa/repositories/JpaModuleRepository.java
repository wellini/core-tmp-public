package cc.roadmaps.core.service.integrations.jpa.repositories;

import cc.roadmaps.core.domain.model.module.Module;
import cc.roadmaps.core.domain.model.module.ModuleRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = Module.class, idClass = Long.class)
public interface JpaModuleRepository extends ModuleRepository {

    @Override
    @Query("SELECT m FROM Module m WHERE m.id = :id")
    @Transactional
    Optional<Module> findModule(@Param("id") UUID id);

    @Override
    @Query("SELECT m FROM Module m RIGHT JOIN Leaf l ON l.moduleId = m.id WHERE l.id = :leafId")
    @Transactional
    Optional<Module> findModuleByLeafId(@Param("leafId") UUID leafId);

    @Override
    @Transactional
    @Modifying
    @Query("DELETE FROM Module m WHERE m.courseId = :courseId")
    void deleteAllByCourseId(@Param("courseId") UUID courseId);

    @Override
    @Transactional
    void save(Module module);
}
