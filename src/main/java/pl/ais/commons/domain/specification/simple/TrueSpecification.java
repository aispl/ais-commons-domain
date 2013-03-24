package pl.ais.commons.domain.specification.simple;

import javax.annotation.concurrent.Immutable;

import pl.ais.commons.domain.specification.Specification;

/**
 * Specification implementation satisfied by any candidate.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class TrueSpecification<C> implements Specification<C> {

    /**
     * Defines singleton instance of {@link TrueSpecification}.
     */
    @SuppressWarnings("rawtypes")
    public static final Specification INSTANCE = new TrueSpecification();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> boolean isSatisfiedBy(final T candidate) {
        return true;
    }

}
