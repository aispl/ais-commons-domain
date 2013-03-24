package pl.ais.commons.domain.specification.simple;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import pl.ais.commons.domain.specification.Specification;

/**
 * Specification implementation satisfied by candidates equal to some predefined value.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class IsEqualSpecification<C> implements Specification<C> {

    private final C value;

    /**
     * Constructs new instance.
     *
     * @param value the value
     */
    public IsEqualSpecification(@Nullable final C value) {
        super();
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> boolean isSatisfiedBy(final T candidate) {
        boolean result;
        if (null == value) {
            result = (null == candidate);
        } else {
            result = value.equals(candidate);
        }
        return result;
    }

}
