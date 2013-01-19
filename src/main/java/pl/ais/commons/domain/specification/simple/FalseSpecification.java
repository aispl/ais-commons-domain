package pl.ais.commons.domain.specification.simple;

import pl.ais.commons.domain.specification.Specification;

/**
 * Specification implementation which is never satisfied.
 *
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public final class FalseSpecification<T> implements Specification<T> {

    /**
     * Defines singleton instance of {@link FalseSpecification}.
     */
    @SuppressWarnings("rawtypes")
    public static final Specification INSTANCE = new FalseSpecification();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        return false;
    }

}
