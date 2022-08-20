package io.roadmaps.core.domain.services.course.operations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ExplainedExecResult {

    private Long id;

    private String message;

    private ExplainedExecResult(Long id) {
        this.id = id;
    }

    public static ExplainedExecResult empty() {
        return new ExplainedExecResult();
    }

    public static ExplainedExecResult identified(Long id) {
        return new ExplainedExecResult(id);
    }
}
