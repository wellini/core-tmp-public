package cc.roadmaps.core.service.integrations.jpa.domain.repositories;

import cc.roadmaps.core.domain.model.course.Course;
import cc.roadmaps.core.domain.model.course.CourseRepository;
import cc.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = Course.class, idClass = UUID.class)
public interface JpaCourseRepository extends CourseRepository {

    @Override
    @Query("SELECT c FROM Course c WHERE c.id = :id")
    @Transactional
    Optional<Course> findCourse(@Param("id") UUID id);

    @Override
    @Query("SELECT c FROM Course c")
    @Transactional
    List<Course> findAllCourses();

    @Override
    @Query("SELECT c FROM Course c RIGHT JOIN Module m ON m.courseId = c.id WHERE m.id = :moduleId")
    @Transactional
    Optional<Course> findCourseByModuleId(@Param("moduleId") UUID moduleId);

    @Override
    @Query("SELECT c FROM Course c " +
            "RIGHT JOIN Module m ON m.courseId = c.id " +
            "RIGHT JOIN Leaf l ON l.moduleId = m.id " +
            "WHERE l.id = :leafId")
    @Transactional
    Optional<Course> findCourseByLeafId(@Param("leafId") UUID leafId);

    @Override
    @Query("SELECT c FROM Course c INNER JOIN CourseAffiliation ca ON c.id = ca.courseId WHERE ca.type = :affiliationType AND ca.userId = :userId")
    @Transactional
    List<Course> findAllCoursesByAffiliationType(@Param("userId") UUID userId, @Param("affiliationType") CourseAffiliationType affiliationType);

    @Override
    @Transactional
    @Modifying
    @Query("DELETE FROM Course c WHERE c.id = :id")
    void delete(@Param("id") UUID id);

    @Override
    @Transactional
    void save(Course course);
}
