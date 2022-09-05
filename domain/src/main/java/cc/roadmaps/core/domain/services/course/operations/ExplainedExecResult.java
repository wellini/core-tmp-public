package cc.roadmaps.core.domain.services.course.operations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ExplainedExecResult {

    private UUID id;

    private String message;

    private ExplainedExecResult(UUID id) {
        this.id = id;
    }

    public static ExplainedExecResult empty() {
        return new ExplainedExecResult();
    }

    public static ExplainedExecResult identified(UUID id) {
        return new ExplainedExecResult(id);
    }
}
