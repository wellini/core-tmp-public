package io.roadmaps.core.configuration;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.common.id.IdExplanationFormatter;
import io.roadmaps.core.domain.model.course.CourseRepository;
import io.roadmaps.core.domain.model.courseAffiliation.CourseAffiliationRepository;
import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.domain.model.module.ModuleRepository;
import io.roadmaps.core.domain.model.user.UserRepository;
import io.roadmaps.core.domain.services.CurrentUserIdProvider;
import io.roadmaps.core.domain.services.course.CourseService;
import io.roadmaps.core.domain.services.course.CourseServiceImpl;
import io.roadmaps.core.domain.services.course.operations.Operation;
import io.roadmaps.core.domain.services.course.operations.OperationsService;
import io.roadmaps.core.domain.services.course.operations.OperationsServiceImpl;
import io.roadmaps.core.domain.services.course.operations.context.OperationExecutionContextFactory;
import io.roadmaps.core.domain.services.course.operations.context.factories.FormattedOperationExecutionContextFactory;
import io.roadmaps.core.domain.services.course.operations.implementations.courseEditPresentation.CourseEditPresentationOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.courseRemove.CourseRemoveOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.createCourse.CreateCourseOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.enrollEncourse.EnrollInCourseOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.leafCreate.LeafCreateOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.leafEditTitle.LeafEditTitleOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.leafMove.LeafMoveOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.leafRemove.LeafRemoveOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.leafUpdateText.LeafUpdateTextOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.moduleCreate.ModuleCreateOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.moduleEditTitle.ModuleEditTitleOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.moduleMove.ModuleMoveOperation;
import io.roadmaps.core.domain.services.course.operations.implementations.moduleRemove.ModuleRemoveOperation;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationService;
import io.roadmaps.core.domain.services.courseAffiliation.CourseAffiliationServiceImpl;
import io.roadmaps.core.domain.services.leaf.LeafService;
import io.roadmaps.core.domain.services.leaf.LeafServiceImpl;
import io.roadmaps.core.domain.services.module.ModuleService;
import io.roadmaps.core.domain.services.module.ModuleServiceImpl;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.domain.services.user.UserServiceImpl;
import io.roadmaps.core.hrid.HRIDUtil;
import io.roadmaps.core.integrations.jpa.idgenerator.JdbcSequenceGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
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
            UserRepository repository,
            @Qualifier("userIdIdSequenceGenerator") Generator<Long> userIdIdSequenceGenerator
    ) {
        return new UserServiceImpl(provider, repository, userIdIdSequenceGenerator);
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
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new CourseEditPresentationOperation(
                contextFactory,
                userService,
                courseAffiliationService,
                courseRepository
        );
    }

    @Bean
    public CourseRemoveOperation getCourseRemoveOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            LeafRepository leafRepository,
            ModuleRepository moduleRepository,
            CourseAffiliationRepository courseAffiliationRepository,
            CourseRepository courseRepository
    ) {
        return new CourseRemoveOperation(
                contextFactory,
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
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository,
            @Qualifier("courseIdSequenceGenerator") Generator<Long> courseIdSequenceGenerator
    ) {
        return new CreateCourseOperation(
                contextFactory,
                userService,
                courseAffiliationService,
                courseRepository,
                courseIdSequenceGenerator
        );
    }

    @Bean
    public EnrollInCourseOperation getEnrollInCourseOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new EnrollInCourseOperation(contextFactory, userService, courseAffiliationService, courseRepository);
    }

    @Bean
    public LeafCreateOperation getLeafCreateOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            ModuleRepository moduleRepository,
            @Qualifier("leafIdSequenceGenerator") Generator<Long> leafIdSequenceGenerator
    ) {
        return new LeafCreateOperation(
                contextFactory,
                userService,
                courseAffiliationService,
                moduleRepository,
                leafIdSequenceGenerator
        );
    }

    @Bean
    public LeafEditTitleOperation getLeafEditTitleOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            LeafRepository leafRepository
    ) {
        return new LeafEditTitleOperation(contextFactory, userService, courseAffiliationService, leafRepository);
    }

    @Bean
    public LeafMoveOperation getLeafMoveOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new LeafMoveOperation(contextFactory, userService, courseAffiliationService, courseRepository);
    }

    @Bean
    public LeafRemoveOperation getLeafRemoveOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            ModuleRepository moduleRepository
    ) {
        return new LeafRemoveOperation(contextFactory, userService, courseAffiliationService, moduleRepository);
    }

    @Bean
    public LeafUpdateTextOperation getLeafUpdateTextOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            LeafRepository leafRepository
    ) {
        return new LeafUpdateTextOperation(contextFactory, userService, courseAffiliationService, leafRepository);
    }

    @Bean
    public ModuleCreateOperation getModuleCreateOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository,
            @Qualifier("moduleIdSequenceGenerator") Generator<Long> moduleIdSequenceGenerator
    ) {
        return new ModuleCreateOperation(contextFactory, userService, courseAffiliationService, courseRepository, moduleIdSequenceGenerator);
    }

    @Bean
    public ModuleEditTitleOperation getModuleEditTitleOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            ModuleRepository moduleRepository
    ) {
        return new ModuleEditTitleOperation(contextFactory, userService, courseAffiliationService, moduleRepository);
    }

    @Bean
    public ModuleMoveOperation getModuleMoveOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new ModuleMoveOperation(contextFactory, userService, courseAffiliationService, courseRepository);
    }

    @Bean
    public ModuleRemoveOperation getModuleRemoveOperation(
            OperationExecutionContextFactory contextFactory,
            UserService userService,
            CourseAffiliationService courseAffiliationService,
            CourseRepository courseRepository
    ) {
        return new ModuleRemoveOperation(contextFactory, userService, courseAffiliationService, courseRepository);
    }

    @Bean(name = "leafIdSequenceGenerator")
    public Generator<Long> leafIdSequenceGenerator(DataSource dataSource) {
        return new JdbcSequenceGenerator(dataSource, "leaves_seq");
    }

    @Bean(name = "moduleIdSequenceGenerator")
    public Generator<Long> moduleIdSequenceGenerator(DataSource dataSource) {
        return new JdbcSequenceGenerator(dataSource, "modules_seq");
    }

    @Bean(name = "courseIdSequenceGenerator")
    public Generator<Long> courseIdSequenceGenerator(DataSource dataSource) {
        return new JdbcSequenceGenerator(dataSource, "courses_seq");
    }

    @Bean(name = "userIdIdSequenceGenerator")
    public Generator<Long> userIdIdSequenceGenerator(DataSource dataSource) {
        return new JdbcSequenceGenerator(dataSource, "users_seq");
    }

    @Bean(name = "authProviderIdIdSequenceGenerator")
    public Generator<Long> authProviderIdIdSequenceGenerator(DataSource dataSource) {
        return new JdbcSequenceGenerator(dataSource, "auth_providers_seq");
    }

    @Bean
    public IdExplanationFormatter getIdExplanationFormatter() {
        return HRIDUtil::serialize;
    }

    @Bean
    public OperationExecutionContextFactory getOperationExecutionContextFactory(IdExplanationFormatter formatter) {
        return new FormattedOperationExecutionContextFactory(formatter);
    }
}
