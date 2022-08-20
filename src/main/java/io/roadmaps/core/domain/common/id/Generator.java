package io.roadmaps.core.domain.common.id;

public interface Generator<V> {
    /**
     * Generate next value
     * 
     * @return The generated value
     */
    V generateNext();
}
