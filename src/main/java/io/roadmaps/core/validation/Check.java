package io.roadmaps.core.validation;

import java.util.function.Supplier;

@FunctionalInterface
public interface Check<T> {

    void configureValidationFlow(ValidationFlow<T> validationFlow, String propertyName, Supplier<T> propertySupplier);
}
