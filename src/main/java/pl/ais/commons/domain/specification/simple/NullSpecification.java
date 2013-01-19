package pl.ais.commons.domain.specification.simple;

import pl.ais.commons.domain.specification.Specification;
import pl.ais.commons.domain.specification.composite.NotSpecification;

/**
 * Specification implementation which is satisfied only if provided candidate is {@code null}.
 *
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public final class NullSpecification<T> implements Specification<T> {

    /**
     * Defines singleton instance of {@link NullSpecification}.
     */
    @SuppressWarnings("rawtypes")
    public static final Specification INSTANCE = new NullSpecification();

    /**
     * Defines singleton instance of {@link NullSpecification} negation.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static final Specification NEGATION = new NotSpecification(new NullSpecification());

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        return (null == candidate);
    }

}
