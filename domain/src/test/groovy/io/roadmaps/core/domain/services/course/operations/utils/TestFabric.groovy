package io.roadmaps.core.domain.services.course.operations.utils

import cc.roadmaps.core.domain.model.course.Course
import cc.roadmaps.core.domain.model.course.enums.CourseCoverTheme
import cc.roadmaps.core.domain.model.user.User
import cc.roadmaps.core.domain.services.course.operations.commands.CommandType
import cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands.CreateCourseCommandDto
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
