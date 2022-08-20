package io.roadmaps.core.hrid;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Random;

/**
 * Human readable identifier
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HRID {

    @Getter
    @EqualsAndHashCode.Include
    private final Long rawId;

    @Getter
    private final String serializedId;

    public static HRID create(Long raw) {
        if(Objects.isNull(raw)) {
            return null;
        } else {
            return new HRID(raw, HRIDUtil.serialize(raw));
        }
    }

    public static HRID fromString(String source) {
        if(Objects.isNull(source)) {
            return null;
        } else {
            return new HRID(HRIDUtil.deserialize(source), source);
        }
    }

    public static HRID randomHRID() {
        return HRID.create(new Random().nextLong());
    }

    @Override
    public String toString() {
        return serializedId;
    }
}
