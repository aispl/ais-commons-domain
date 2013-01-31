package pl.ais.commons.domain.specification.simple;

import javax.annotation.Nonnull;

import pl.ais.commons.domain.specification.Specification;

/**
 * {@link Specification} implementation which is satisfied only if candidate is after predefined bound.
 *
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public final class AfterSpecification<T extends Comparable<T>> implements Specification<T> {

    private final T bound;

    /**
     * Constructs new instance.
     *
     * @param bound the bound
     */
    public AfterSpecification(@Nonnull final T bound) {
        super();
        this.bound = bound;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        return (bound.compareTo(candidate) < 0);
    }
}
