package io.roadmaps.core.validation;

import java.util.Objects;

public class Rules {

    public static <T> Rule<T> notNull() {
        return Objects::nonNull;
    }

    public static Rule<String> notEmpty() {
        return (String str) -> Objects.nonNull(str) && !str.isEmpty();
    }

    public static Rule<String> notBlank() {
        return (String str) -> Objects.nonNull(str) && !str.isBlank();
    }

    public static Rule<String> maxLength(int max) {
        return Rules.maxLength(max, LengthComparisonMode.STANDARD);
    }

    public static Rule<String> maxLength(int max, LengthComparisonMode mode) {
        if(mode == LengthComparisonMode.TRIMMED) {
            return (String str) -> Objects.isNull(str) || str.trim().length() <= max;
        } else {
            return (String str) -> Objects.isNull(str) || str.length() <= max;
        }
    }

    public static Rule<String> minAndMaxLength(int min, int max) {
        return Rules.minAndMaxLength(min, max, LengthComparisonMode.STANDARD);
    }

    public static Rule<String> minAndMaxLength(int min, int max, LengthComparisonMode mode) {
        if(mode == LengthComparisonMode.TRIMMED) {
            return (String str) -> Objects.isNull(str) || (str.trim().length() >= min && str.trim().length() <= max);
        } else {
            return (String str) -> Objects.isNull(str) || (str.length() >= min && str.length() < max);
        }
    }

    public static Rule<Integer> min(int min) {
        return (Integer num) -> Objects.nonNull(num) && num >= min;
    }

    public static Rule<Integer> max(int max) {
        return (Integer num) -> Objects.nonNull(num) && num <= max;
    }

    public static Rule<Integer> minAndMax(int min, int max) {
        return (Integer num) -> Objects.nonNull(num) && num >= min && num <= max;
    }

    public enum LengthComparisonMode {
        TRIMMED,
        STANDARD
    }
}
