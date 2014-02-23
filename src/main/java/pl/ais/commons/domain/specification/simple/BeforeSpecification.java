package pl.ais.commons.domain.specification.simple;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import pl.ais.commons.domain.specification.Specification;

/**
 * {@link Specification} implementation which is satisfied only if candidate is before predefined bound.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class BeforeSpecification<C extends Comparable<? super C>> implements Specification<C> {

    private final C bound;

    /**
     * Constructs new instance.
     *
     * @param bound the bound
     */
    public BeforeSpecification(@Nonnull final C bound) {
        super();
        if (null == bound) {
            throw new IllegalArgumentException("Bound is required.");
        }
        this.bound = bound;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> boolean isSatisfiedBy(final T candidate) {
        return bound.compareTo(candidate) > 0;
    }
}
