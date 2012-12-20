package pl.ais.commons.domain.specification.composite;

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
    Specification<T> and(Specification<T> other);

    /**
     * @return the specification being negation of this one
     */
    Specification<T> not();

    /**
     * Returns the specification being disjunction of this one and the other.
     *
     * @param other the other specification
     * @return the specification being disjunction of this one and the other
     */
    Specification<T> or(Specification<T> other);

}
