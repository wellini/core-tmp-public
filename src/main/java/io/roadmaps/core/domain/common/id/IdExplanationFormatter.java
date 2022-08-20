package io.roadmaps.core.domain.common.id;

@FunctionalInterface
public interface IdExplanationFormatter {

    String format(Long id);
}
