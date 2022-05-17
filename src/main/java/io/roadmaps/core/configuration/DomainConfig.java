package io.roadmaps.core.configuration;

import io.roadmaps.core.domain.model.course.CourseRepository;
import io.roadmaps.core.domain.model.leaf.LeafRepository;
import io.roadmaps.core.domain.model.module.ModuleRepository;
import io.roadmaps.core.domain.model.user.UserRepository;
import io.roadmaps.core.domain.services.CurrentUserIdProvider;
import io.roadmaps.core.domain.services.course.CourseService;
import io.roadmaps.core.domain.services.course.CourseServiceImpl;
import io.roadmaps.core.domain.services.leaf.LeafService;
import io.roadmaps.core.domain.services.leaf.LeafServiceImpl;
import io.roadmaps.core.domain.services.module.ModuleService;
import io.roadmaps.core.domain.services.module.ModuleServiceImpl;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.domain.services.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public CourseService getCourseService(CourseRepository courseRepository, UserService userService, CurrentUserIdProvider provider) {
        return new CourseServiceImpl(courseRepository, userService, provider);
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
    public UserService getUserService(CurrentUserIdProvider provider, UserRepository repository) {
        return new UserServiceImpl(provider, repository);
    }
}
