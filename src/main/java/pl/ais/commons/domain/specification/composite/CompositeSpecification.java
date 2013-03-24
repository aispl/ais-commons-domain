package pl.ais.commons.domain.specification.composite;

import javax.annotation.Nonnull;

import pl.ais.commons.domain.specification.Specification;

/**
 * Defines the API contract for composite specification.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public interface CompositeSpecification<C> extends Specification<C> {

    /**
     * Returns the specification being conjunction of this one and the other.
     *
     * @param other the other specification
     * @return the specification being conjunction of this one and the other
     */
    @Nonnull
    Specification<C> and(@Nonnull Specification<C> other);

    /**
     * Returns the components building this specification.
     *
     * @return an array of the components building this specification (fresh copy - the modification made to it,
     *         will not affect this specification anyhow)
     */
    @Nonnull
    Specification<C>[] getComponents();

    /**
     * @return the specification being negation of this one
     */
    @Nonnull
    Specification<C> not();

    /**
     * Returns the specification being disjunction of this one and the other.
     *
     * @param other the other specification
     * @return the specification being disjunction of this one and the other
     */
    @Nonnull
    Specification<C> or(@Nonnull Specification<C> other);

}
