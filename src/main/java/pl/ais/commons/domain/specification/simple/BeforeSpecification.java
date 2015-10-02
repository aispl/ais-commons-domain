package pl.ais.commons.domain.specification.simple;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * {@link Predicate} implementation which is satisfied only if candidate is before predefined bound.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class BeforeSpecification<C extends Comparable<? super C>> implements Predicate<C> {

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
     * Indicates whether some other object is "equal to" this one.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (object instanceof BeforeSpecification)) {
            final BeforeSpecification other = (BeforeSpecification) object;
            result = Objects.equals(bound, other.bound);
        }
        return result;
    }

    /**
     * @return a hash code value for this specification
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(bound);
    }

    @Override
    public boolean test(final C candidate) {
        return bound.compareTo(candidate) > 0;
    }

    /**
     * @return a String representation of this specification
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Is less than ")
                                  .append(bound)
                                  .toString();
    }

}
