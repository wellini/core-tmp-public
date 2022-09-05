package cc.roadmaps.core.domain.exceptions;

import cc.roadmaps.core.domain.model.course.Course;
import cc.roadmaps.core.domain.model.courseAffiliation.CourseAffiliation;
import cc.roadmaps.core.domain.model.leaf.Leaf;
import cc.roadmaps.core.domain.model.leaf.LinkLeaf;
import cc.roadmaps.core.domain.model.leaf.TextLeaf;
import cc.roadmaps.core.domain.model.module.Module;
import cc.roadmaps.core.domain.model.user.User;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static java.util.Map.entry;

public class EntityNotFoundDomainException extends DomainException {

    private static final String TEMPLATE = "Not found %s with %s";
    private static final String SIMPLE_TEMPLATE = "Not found %s";
    private static final String DEFAULT_ENTITY_NAME = "entity";
    private static final Map<Class<?>, String> domainEntityNames = Map.ofEntries(
            entry(User.class, "user"),
            entry(Course.class, "course"),
            entry(Module.class, "module"),
            entry(Leaf.class, "leaf"),
            entry(LinkLeaf.class, "link leaf"),
            entry(TextLeaf.class, "text leaf"),
            entry(CourseAffiliation.class, "affiliation")
    );

    private EntityNotFoundDomainException(String entityName, String id) {
        super(TEMPLATE.formatted(
                getEntityName(entityName),
                Optional.ofNullable(id)
                        .map(s -> "id {" + s + "}")
                        .orElse("passed id")
        ));
    }

    private EntityNotFoundDomainException(Class<?> domainEntityClass, String id) {
        super(TEMPLATE.formatted(
                getEntityName(domainEntityNames.get(domainEntityClass)),
                Optional.ofNullable(id)
                        .map(s -> "id {" + s + "}")
                        .orElse("passed id")
        ));
    }

    private EntityNotFoundDomainException(Class<?> domainEntityClass) {
        super(SIMPLE_TEMPLATE.formatted(getEntityName(domainEntityNames.get(domainEntityClass))));
    }

    public static Supplier<EntityNotFoundDomainException> createExceptionSupplier(UUID id) {
        return () -> new EntityNotFoundDomainException((String) null, id.toString());
    }

    public static Supplier<EntityNotFoundDomainException> createExceptionSupplier(Class<?> domainEntityClass, UUID id) {
        return () -> new EntityNotFoundDomainException(domainEntityClass, id.toString());
    }

    public static Supplier<EntityNotFoundDomainException> createSimpleExceptionSupplier(Class<?> domainEntityClass) {
        return () -> new EntityNotFoundDomainException(domainEntityClass);
    }

    private static String getEntityName(String s) {
        return Optional.ofNullable(s).orElse(DEFAULT_ENTITY_NAME);
    }
}
