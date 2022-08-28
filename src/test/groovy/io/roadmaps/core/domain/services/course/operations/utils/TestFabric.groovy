package io.roadmaps.core.domain.services.course.operations.utils

import io.roadmaps.core.domain.model.course.Course
import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme
import io.roadmaps.core.domain.model.module.Module
import io.roadmaps.core.domain.model.user.User
import io.roadmaps.core.domain.services.course.operations.commands.CommandType
import io.roadmaps.core.integrations.web.rest.api.course.dtos.commands.CreateCourseCommandDto
import org.apache.commons.lang3.RandomStringUtils

class TestFabric {

    static User identifiedUser() {
        def user = new User()
        user.id = UUID.randomUUID()
        return user
    }

    static TestCommand validCommand(CommandType type) {
        def testCommand = new TestCommand()
        testCommand.setCourseId(UUID.randomUUID())
        testCommand.setCommandType(type)
        return testCommand
    }

    static Course validCourse(User author) {
        def dto = new CreateCourseCommandDto(
                RandomStringUtils.randomAlphabetic(100),
                CourseCoverTheme.BLACK
        )
        return Course.create(author, dto)
    }

    static Module validModule() {
        return Module.create(RandomStringUtils.randomAlphabetic(100))
    }
}
