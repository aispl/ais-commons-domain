package pl.ais.commons.domain.specification.composite;

import pl.ais.commons.domain.specification.Specification;

/**
 * Base class for composite specifications.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
abstract class AbstractCompositeSpecification<T> implements CompositeSpecification<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Specification<T> and(final Specification<T> other) {
        return new AndSpecification<T>(this, other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<T> not() {
        return new NotSpecification<T>(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Specification<T> or(final Specification<T> other) {
        return new OrSpecification<T>(this, other);
    }

}
