package cc.roadmaps.validation.utils;

import cc.roadmaps.validation.api.Rule;

public class TestRules {

    public static Rule<Object> alwaysFailing() {
        return (Object o) -> false;
    }

    public static Rule<Object> alwaysAccepting() {
        return (Object o) -> true;
    }
}
