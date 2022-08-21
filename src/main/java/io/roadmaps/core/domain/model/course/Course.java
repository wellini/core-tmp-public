package io.roadmaps.core.domain.model.course;

import io.roadmaps.core.domain.model.course.affiliationsregistry.AffiliationsRegistry;
import io.roadmaps.core.domain.model.course.events.CourseCreationEvent;
import io.roadmaps.core.domain.model.course.events.MoveLeafEvent;
import io.roadmaps.core.domain.model.course.events.MoveModuleEvent;
import io.roadmaps.core.domain.model.course.presentation.Presentation;
import io.roadmaps.core.domain.model.course.presentation.events.EditPresentationEvent;
import io.roadmaps.core.domain.model.courseAffiliation.enums.CourseAffiliationType;
import io.roadmaps.core.domain.model.leaf.Leaf;
import io.roadmaps.core.domain.model.module.Module;
import io.roadmaps.core.domain.model.user.User;
import io.roadmaps.core.exception.EntityNotFoundException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
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

    private AffiliationsRegistry affiliationsRegistry;

    private Course(User author, CourseCreationEvent creationCommand) {
        id = UUID.randomUUID();
        this.author = author;
        presentation = Presentation.create(
                creationCommand.getTitle(),
                creationCommand.getCoverTheme()
        );
        affiliationsRegistry = AffiliationsRegistry.init(id);
        affiliationsRegistry.addAffiliation(author.getId(), CourseAffiliationType.TEACHER);
    }

    public static Course create(User author, CourseCreationEvent creationCommand) {
        return new Course(author, creationCommand);
    }

    public CourseAffiliationType getAffiliationType(UUID userId) {
        return affiliationsRegistry.getAffiliationType(userId);
    }

    public void addModule(Module module, int orderId) {
        modules.add(Math.min(orderId, modules.size()), module);
    }

    public void removeModule(UUID moduleId) {
        Module targetModule = modules.stream()
                .filter(module -> module.getId().equals(moduleId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        this.modules.remove(targetModule);
    }

    public void enrollInCourse(UUID userId) {
        affiliationsRegistry.addAffiliation(userId, CourseAffiliationType.STUDENT);
    }

    public void editPresentation(EditPresentationEvent command) {
        presentation.edit(command);
    }

    public void moveLeaf(UUID leafId, MoveLeafEvent command) {
        Module fromModule = modules.stream()
                .filter(module -> module.hasLeaf(leafId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        Module destinationModule = modules.stream()
                .filter(module -> module.getId().equals(command.getDestinationModuleId()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        Leaf leaf = fromModule.getLeaf(leafId);
        destinationModule.addLeaf(leaf, command.getDestinationOrderId());
        fromModule.removeLeaf(leafId);
    }

    public void moveModule(UUID moduleId, MoveModuleEvent command) {
        Module module = modules.stream()
                .filter(m -> m.getId().equals(moduleId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        modules.remove(module);
        modules.add(Math.min(command.getDestinationOrderId(), modules.size()), module);
    }
}
