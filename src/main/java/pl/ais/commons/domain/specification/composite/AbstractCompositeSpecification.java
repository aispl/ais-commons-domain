package pl.ais.commons.domain.specification.composite;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.ais.commons.domain.specification.Specification;

/**
 * Base class for composite specifications.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
abstract class AbstractCompositeSpecification<C> implements CompositeSpecification<C> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<C> and(final Specification<C> other) {
        return new AndSpecification<>(this, other);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (null != object) && (getClass() == object.getClass())) {
            final AbstractCompositeSpecification other = (AbstractCompositeSpecification) object;
            result = ArrayUtils.isEquals(getComponents(), other.getComponents());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getComponents()).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<C> not() {
        return new NotSpecification<>(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<C> or(final Specification<C> other) {
        return new OrSpecification<>(this, other);
    }

}
