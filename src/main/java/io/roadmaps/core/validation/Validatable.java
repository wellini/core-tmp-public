package io.roadmaps.core.validation;

import io.roadmaps.core.exception.ValidationException;

public interface Validatable {

    void validate() throws ValidationException;
}
