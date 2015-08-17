package pl.ais.commons.domain.specification.simple;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.function.Predicate;

/**
 * {@link Predicate} implementation which is satisfied only if candidate is after predefined bound.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class AfterSpecification<C extends Comparable<? super C>> implements Predicate<C> {

    private final C bound;

    /**
     * Constructs new instance.
     *
     * @param bound the bound
     */
    public AfterSpecification(@Nonnull final C bound) {
        super();
        if (null == bound) {
            throw new IllegalArgumentException("Bound is required.");
        }
        this.bound = bound;
    }

    @Override
    public boolean test(final C candidate) {
        return bound.compareTo(candidate) < 0;
    }

}
