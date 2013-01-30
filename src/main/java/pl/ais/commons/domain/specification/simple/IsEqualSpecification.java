package pl.ais.commons.domain.specification.simple;

import javax.annotation.Nullable;

import pl.ais.commons.domain.specification.Specification;

/**
 * Specification implementation satisfied by candidates equal to some predefined value.
 *
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public final class IsEqualSpecification<T> implements Specification<T> {

    private final T value;

    /**
     * Constructs new instance.
     *
     * @param value the value
     */
    public IsEqualSpecification(@Nullable final T value) {
        super();
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        return ((null == value) && (null == candidate)) || value.equals(candidate);
    }

}
