package io.github.srhojo.demo.gateway.services.mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Interface who define methods for mappers
 * </p>
 *
 * @author srhojo
 * @see <a href="https://github.com/srhojo">GitHub</a>
 *
 * @param <I> Inner Object
 * @param <O> Outer Object
 *
 */
public interface Mapper<I, O> {

    /**
     * Map method from outer to inner object
     *
     * @param outer object
     * @return Inner object
     */
    I mapToInner(O outer);

    /**
     * Wrapper method to map a list of outer objects.
     *
     * @param outer List of outer object
     * @return List of an inner objects
     */
    default List<I> toInner(final List<O> outer) {
        return outer != null ? outer.stream().map(this::mapToInner).collect(Collectors.toList())
                : Collections.emptyList();
    }

    /**
     * Map method from inner to outer object
     *
     * @param inner object to map
     * @return outer object
     */
    O mapToOuter(I inner);

    /**
     * @param inner List of inner
     * @return List of an outer objects
     */
    default List<O> toOuter(final List<I> inner) {
        return inner != null ? inner.stream().map(this::mapToOuter).collect(Collectors.toList())
                : Collections.emptyList();
    }
}
