package io.roadmaps.core.validation.utils;

import io.roadmaps.core.validation.Rule;

public class TestRules {

    public static Rule<Object> alwaysFailing() {
        return (Object o) -> false;
    }

    public static Rule<Object> alwaysAccepting() {
        return (Object o) -> true;
    }
}
