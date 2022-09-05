package cc.roadmaps.validation.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;

public class ValidationFlow<T> {

    private final ValidationFlow<?> parent;

    private final String propertyName;

    private final Supplier<T> propertySupplier;

    private boolean alive;

    private final List<ValidationFlow<?>> children = new ArrayList<>();

    private final List<String> errors = new ArrayList<>();

    private ValidationFlow(ValidationFlow<?> parent, String propertyName, Supplier<T> propertySupplier, boolean alive) {
        this.parent = parent;
        this.propertyName = propertyName;
        this.propertySupplier = propertySupplier;
        this.alive = alive;
    }

    /**
     * Create flow
     * @return
     */
    public static ValidationFlow<?> start() {
        return new ValidationFlow<Object>(
                null,
                null,
                null,
                true
        );
    }

    /**
     * Create subflow for specific property
     * @param propertyName
     * @param propertySupplier
     * @param <S>
     * @return
     */
    public <S> ValidationFlow<S> forProperty(String propertyName, Supplier<S> propertySupplier) {
        ValidationFlow<S> subFlow = new ValidationFlow<>(
                this,
                propertyName,
                propertySupplier,
                alive
        );
        children.add(subFlow);
        return subFlow;
    }

    /**
     * Add a set of requirements
     * @param check
     * @return
     */
    public ValidationFlow<T> addCheck(Check<T> check) {
        check.configureValidationFlow(this, propertyName, propertySupplier);
        return this;
    }

    /**
     * Check rule and save error if it happens. all following checks and subflows will be ignored
     * @param rule
     * @param message
     * @return
     */
    public ValidationFlow<T> strictlyRequire(Rule<T> rule, String message) {
        if(alive && !isThisRoot()) {
            boolean passed = rule.check(propertySupplier.get());
            if(!passed) {
                errors.add(message);
                breakFlow();
            }
        }
        return this;
    }

    /**
     * Check rule and save error if it happens.
     * @param rule
     * @param message
     * @return
     */
    public ValidationFlow<T> require(Rule<T> rule, String message) {
        if(alive && !isThisRoot()) {
            boolean passed = rule.check(propertySupplier.get());
            if(!passed) {
                errors.add(message);
            }
        }
        return this;
    }

    public boolean hasErrors() {
        return !errors.isEmpty() || children.stream().anyMatch(ValidationFlow::hasErrors);
    }

    public ValidationReport getValidationReport() {
        ValidationReport validationReport = ValidationReport.create();
        Stack<String> locationStack = new Stack<>();
        appendErrorsToReport(validationReport, locationStack, this);
        return validationReport;
    }

    private void appendErrorsToReport(ValidationReport report, Stack<String> locationStack, ValidationFlow<?> flow) {
        for(ValidationFlow<?> child : flow.children) {
            locationStack.add(child.propertyName);
            report.add(String.join(".", locationStack.stream().toList()), child.errors);
            appendErrorsToReport(report, locationStack, child);
            locationStack.pop();
        }
    }

    public ValidationFlow<?> and() {
        if(parent != null) {
            return parent;
        } else {
            return this;
        }
    }

    public <THROWABLE extends Throwable> ValidationFlow<T> ifHasErrorsThrow(Function<ValidationReport, THROWABLE> throwableProducer) throws THROWABLE {
        if(hasErrors()) {
            throw throwableProducer.apply(getValidationReport());
        } else {
            return this;
        }
    }

    private boolean isThisRoot() {
        return parent == null;
    }

    private void breakFlow() {
        alive = false;
    }
}
