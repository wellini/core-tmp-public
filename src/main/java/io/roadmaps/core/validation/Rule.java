package io.roadmaps.core.validation;

@FunctionalInterface
public interface Rule<T> {

    boolean check(T property);
}
