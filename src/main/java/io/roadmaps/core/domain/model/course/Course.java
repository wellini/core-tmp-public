package io.roadmaps.core.domain.model.course;

import io.roadmaps.core.domain.model.course.commands.CourseCreationCommand;
import io.roadmaps.core.domain.model.course.commands.EnrollInCourseCommand;
import io.roadmaps.core.domain.model.course.commands.ModuleCreationCommand;
import io.roadmaps.core.domain.model.course.presentation.Presentation;
import io.roadmaps.core.domain.model.course.presentation.commands.EditPresentationCommand;
import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliation;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.user.User;
import io.roadmaps.core.exception.EntityNotFoundException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Course {

    @EqualsAndHashCode.Include
    @Getter
    private UUID id;

    @Getter
    private Presentation presentation;

    @Getter
    private User author;

    @Getter
    private List<Module> modules = new ArrayList<>();

    @Getter
    private Set<CourseAffiliation> courseAffiliations = new HashSet<>();

    private Map<UUID, CourseAffiliationType> affiliations = new HashMap();

    private Course(User author, CourseCreationCommand creationCommand) {
        id = UUID.randomUUID();
        this.author = author;
        presentation = Presentation.create(
                creationCommand.getTitle(),
                creationCommand.getCoverTheme(),
                creationCommand.getCoverUrl()
        );
        this.addCourseAffiliation(author.getId(), CourseAffiliationType.TEACHER);
    }

    public static Course create(User author, CourseCreationCommand creationCommand) {
        return new Course(author, creationCommand);
    }

    public Course setCourseAffiliations(Set<CourseAffiliation> courseAffiliations) {
        this.courseAffiliations = courseAffiliations;
        refreshAffiliations();
        return this;
    }

    public CourseAffiliationType getAffiliationType(UUID userId) {
        return affiliations.get(userId);
    }

    public UUID addModule(ModuleCreationCommand moduleCreationCommand) {
        Module module = Module.create(moduleCreationCommand.getTitle());
        if(moduleCreationCommand.getOrderId() < this.modules.size()) {
            modules.add(moduleCreationCommand.getOrderId(), module);
        } else {
            modules.add(module);
        }
        return module.getId();
    }

    public void removeModule(UUID moduleId) {
        Module targetModule = modules.stream()
                .filter(module -> module.getId().equals(moduleId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        this.modules.remove(targetModule);
    }

    public void enrollInCourse(EnrollInCourseCommand enrollCommand) {
        addCourseAffiliation(enrollCommand.getUserId(), CourseAffiliationType.STUDENT);
    }

    public void editPresentation(EditPresentationCommand command) {
        this.presentation.edit(command);
    }

    private void addCourseAffiliation(UUID userId, CourseAffiliationType affiliationType) {
        log.debug("Connect user with id {{}} with course with id {{}} as {{}}", userId, id, affiliationType);
        CourseAffiliation courseAffiliation = CourseAffiliation.create(id, userId, affiliationType);
        courseAffiliations.add(courseAffiliation);
        refreshAffiliations();
    }

    private void refreshAffiliations() {
        affiliations = new HashMap<>();
        courseAffiliations.forEach(ca -> affiliations.put(ca.getUserId(), ca.getType()));
    }
}
