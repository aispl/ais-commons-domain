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
    public Specification<T> and(final Specification<T> other) {
        return new AndSpecification<>(this, other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<T> not() {
        return new NotSpecification<>(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<T> or(final Specification<T> other) {
        return new OrSpecification<>(this, other);
    }

}
