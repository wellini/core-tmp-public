package cc.roadmaps.core.domain.services.course.operations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(staticName = "create")
public class OperationResult {

    private final UUID courseId;

    private final UUID moduleId;

    private final UUID leafId;

    private final String message;
}
