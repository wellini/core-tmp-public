package io.roadmaps.core.domain.services.course.operations.utils

import io.roadmaps.core.domain.services.course.operations.commands.Command
import io.roadmaps.core.domain.services.course.operations.commands.CommandType

class TestCommand implements Command {

    def UUID courseId

    def CommandType commandType
}