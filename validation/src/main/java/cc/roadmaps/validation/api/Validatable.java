package cc.roadmaps.validation.api;

import cc.roadmaps.validation.exceptions.ValidationException;

public interface Validatable {

    void validate() throws ValidationException;
}
