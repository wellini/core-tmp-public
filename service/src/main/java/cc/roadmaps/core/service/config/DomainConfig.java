package cc.roadmaps.core.service.config;

import cc.roadmaps.core.domain.model.course.CourseRepository;
import cc.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository;
import cc.roadmaps.core.domain.model.leaf.LeafRepository;
import cc.roadmaps.core.domain.model.module.ModuleRepository;
import cc.roadmaps.core.domain.model.user.UserRepository;
import cc.roadmaps.core.domain.services.CurrentUserIdProvider;
import cc.roadmaps.core.domain.services.course.CourseService;
import cc.roadmaps.core.domain.services.course.CourseServiceImpl;
import cc.roadmaps.core.domain.services.course.operations.Operation;
import cc.roadmaps.core.domain.services.course.operations.OperationsService;
import cc.roadmaps.core.domain.services.course.operations.OperationsServiceImpl;
import cc.roadmaps.core.domain.services.course.operations.implementations.courseEditPresentation.CourseEditPresentationOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.courseRemove.CourseRemoveOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.createCourse.CreateCourseOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.enrollEncourse.EnrollInCourseOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.leafCreate.LeafCreateOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.leafEditTitle.LeafEditTitleOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.leafMove.LeafMoveOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.leafRemove.LeafRemoveOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.leafUpdateText.LeafUpdateTextOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.moduleCreate.ModuleCreateOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.moduleEditTitle.ModuleEditTitleOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.moduleMove.ModuleMoveOperation;
import cc.roadmaps.core.domain.services.course.operations.implementations.moduleRemove.ModuleRemoveOperation;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import cc.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationServiceImpl;
import cc.roadmaps.core.domain.services.leaf.LeafService;
import cc.roadmaps.core.domain.services.leaf.LeafServiceImpl;
import cc.roadmaps.core.domain.services.module.ModuleService;
import cc.roadmaps.core.domain.services.module.ModuleServiceImpl;
import cc.roadmaps.core.domain.services.user.UserService;
import cc.roadmaps.core.domain.services.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DomainConfig {

    @Bean
    public CourseService getCourseService(
            CourseRepository courseRepository,
            UserService userService
    ) {
        return new CourseServiceImpl(courseRepository, userService);
    }

    @Bean
    public ModuleService getModuleService(ModuleRepository moduleRepository) {
        return new ModuleServiceImpl(moduleRepository);
    }

    @Bean
    public LeafService getLeafService(LeafRepository leafRepository) {
        return new LeafServiceImpl(leafRepository);
    }

    @Bean
    public UserService getUserService(
            CurrentUserIdProvider provider,
            UserRepository repository
    ) {
        return new UserServiceImpl(provider, repository);
    }

    @Bean
    public CourseAffiliationService getCourseAffiliationService(CourseAffiliationRepository repository) {
        return new CourseAffiliationServiceImpl(repository);
    }

    @Bean
    public OperationsService getOperationsService(List<Operation> operations) {
        return new OperationsServiceImpl(operations);
    }

    @Bean
    public CourseEditPresentationOperation getCourseEditPresentationOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new CourseEditPresentationOperation(
                userService,
                courseAffiliationService,
                courseRepository
        );
    }

    @Bean
    public CourseRemoveOperation getCourseRemoveOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            LeafRepository leafRepository,
            ModuleRepository moduleRepository,
            CourseAffiliationRepository courseAffiliationRepository,
            CourseRepository courseRepository
    ) {
        return new CourseRemoveOperation(
                userService,
                courseAffiliationService,
                leafRepository,
                moduleRepository,
                courseAffiliationRepository,
                courseRepository
        );
    }

    @Bean
    public CreateCourseOperation getCreateCourseOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new CreateCourseOperation(
                userService,
                courseAffiliationService,
                courseRepository
        );
    }

    @Bean
    public EnrollInCourseOperation getEnrollInCourseOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new EnrollInCourseOperation(userService, courseAffiliationService, courseRepository);
    }

    @Bean
    public LeafCreateOperation getLeafCreateOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            ModuleRepository moduleRepository
    ) {
        return new LeafCreateOperation(
                userService,
                courseAffiliationService,
                moduleRepository
        );
    }

    @Bean
    public LeafEditTitleOperation getLeafEditTitleOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            LeafRepository leafRepository
    ) {
        return new LeafEditTitleOperation(userService, courseAffiliationService, leafRepository);
    }

    @Bean
    public LeafMoveOperation getLeafMoveOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new LeafMoveOperation(userService, courseAffiliationService, courseRepository);
    }

    @Bean
    public LeafRemoveOperation getLeafRemoveOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            ModuleRepository moduleRepository
    ) {
        return new LeafRemoveOperation(userService, courseAffiliationService, moduleRepository);
    }

    @Bean
    public LeafUpdateTextOperation getLeafUpdateTextOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            LeafRepository leafRepository
    ) {
        return new LeafUpdateTextOperation(userService, courseAffiliationService, leafRepository);
    }

    @Bean
    public ModuleCreateOperation getModuleCreateOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new ModuleCreateOperation(userService, courseAffiliationService, courseRepository);
    }

    @Bean
    public ModuleEditTitleOperation getModuleEditTitleOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            ModuleRepository moduleRepository
    ) {
        return new ModuleEditTitleOperation(userService, courseAffiliationService, moduleRepository);
    }

    @Bean
    public ModuleMoveOperation getModuleMoveOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new ModuleMoveOperation(userService, courseAffiliationService, courseRepository);
    }

    @Bean
    public ModuleRemoveOperation getModuleRemoveOperation(
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new ModuleRemoveOperation(userService, courseAffiliationService, courseRepository);
    }
}
