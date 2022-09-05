package cc.roadmaps.validation.api;

@FunctionalInterface
public interface Rule<T> {

    boolean check(T property);
}
