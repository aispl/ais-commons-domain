package pl.ais.commons.domain.specification.simple;

import pl.ais.commons.domain.specification.Specification;

/**
 * Specification implementation satisfied by any candidate.
 *
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public final class TrueSpecification<T> implements Specification<T> {

    /**
     * Defines singleton instance of {@link TrueSpecification}.
     */
    @SuppressWarnings("rawtypes")
    public static final Specification INSTANCE = new TrueSpecification();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        return true;
    }

}
