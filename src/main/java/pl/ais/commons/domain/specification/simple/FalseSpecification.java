package pl.ais.commons.domain.specification.simple;

import pl.ais.commons.domain.specification.Specification;

/**
 * Specification implementation which is never satisfied.
 *
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public class FalseSpecification<T> implements Specification<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        return false;
    }

}
