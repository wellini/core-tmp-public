package io.roadmaps.core.validation;

public interface Rule<T> {
    boolean check(T property);
}
