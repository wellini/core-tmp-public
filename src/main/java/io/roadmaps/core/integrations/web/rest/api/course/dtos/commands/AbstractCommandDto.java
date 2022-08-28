package io.roadmaps.core.integrations.web.rest.api.course.dtos.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.roadmaps.core.domain.services.course.operations.commands.Command;
import io.roadmaps.core.domain.services.course.operations.commands.CommandType;
import io.roadmaps.core.exception.ValidationException;
import io.roadmaps.core.validation.Rules;
import io.roadmaps.core.validation.Validatable;
import io.roadmaps.core.validation.ValidationFlow;
import lombok.Data;

import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "commandType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CourseEditPresentationCommandDto.class, name = "COURSE_EDIT_PRESENTATION"),
        @JsonSubTypes.Type(value = CourseRemoveCommandDto.class, name = "COURSE_REMOVE"),
        @JsonSubTypes.Type(value = CreateCourseCommandDto.class, name = "COURSE_CREATE"),
        @JsonSubTypes.Type(value = EnrollInCourseCommandDto.class, name = "ENROLL_IN_COURSE"),
        @JsonSubTypes.Type(value = LeafCreateCommandDto.class, name = "LEAF_CREATE"),
        @JsonSubTypes.Type(value = LeafEditTitleCommandDto.class, name = "LEAF_EDIT_TITLE"),
        @JsonSubTypes.Type(value = LeafMoveCommandDto.class, name = "LEAF_MOVE"),
        @JsonSubTypes.Type(value = LeafRemoveCommandDto.class, name = "LEAF_REMOVE"),
        @JsonSubTypes.Type(value = LeafUpdateTextCommandDto.class, name = "LEAF_UPDATE_TEXT"),
        @JsonSubTypes.Type(value = ModuleCreateCommandDto.class, name = "MODULE_CREATE"),
        @JsonSubTypes.Type(value = ModuleEditTitleCommandDto.class, name = "MODULE_EDIT_TITLE"),
        @JsonSubTypes.Type(value = ModuleMoveCommandDto.class, name = "MODULE_MOVE"),
        @JsonSubTypes.Type(value = ModuleRemoveCommandDto.class, name = "MODULE_REMOVE")
})
@Data
public abstract class AbstractCommandDto implements Validatable, Command {

    private CommandType commandType;

    @Override
    public void validate() throws ValidationException {

        // @formatter:off
        ValidationFlow<?> validationFlow = ValidationFlow.start()
                .forProperty("commandType", this::getCommandType)
                    .strictlyRequire(Rules.notNull(), "Set command type")
                    .and();
        // @formatter:off

        configureSpecificValidations(validationFlow);
        validationFlow.ifHasErrorsThrow(ValidationException::new);
    }

    @JsonIgnore
    @Override
    public UUID getCourseId() {
        return Command.super.getCourseId();
    }

    @JsonIgnore
    @Override
    public UUID getModuleId() {
        return Command.super.getModuleId();
    }

    @JsonIgnore
    @Override
    public UUID getLeafId() {
        return Command.super.getLeafId();
    }

    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {}
}
