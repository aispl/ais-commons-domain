package pl.ais.commons.domain.specification.composite;

import java.util.Collection;

import javax.annotation.Nonnull;

import pl.ais.commons.domain.specification.Specification;

/**
 * Defines the API contract for composite specification.
 *
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public interface CompositeSpecification<T> extends Specification<T> {

    /**
     * Returns the specification being conjunction of this one and the other.
     *
     * @param other the other specification
     * @return the specification being conjunction of this one and the other
     */
    @Nonnull
    Specification<T> and(@Nonnull Specification<T> other);

    /**
     * @return unmodifiable view of the components building this specification
     */
    @Nonnull
    Collection<? extends Specification<T>> getComponents();

    /**
     * @return the specification being negation of this one
     */
    @Nonnull
    Specification<T> not();

    /**
     * Returns the specification being disjunction of this one and the other.
     *
     * @param other the other specification
     * @return the specification being disjunction of this one and the other
     */
    @Nonnull
    Specification<T> or(@Nonnull Specification<T> other);

}
