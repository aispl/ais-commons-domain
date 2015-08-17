package pl.ais.commons.domain.specification.simple;

import javax.annotation.concurrent.Immutable;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * {@link Predicate} implementation which is satisfied only if provided character sequence length is limited
 * to predefined number of characters, or the character sequence is {@code null}.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
@Immutable
public final class FitIntoSpecification implements Predicate<CharSequence> {

    private final int upperLimit;

    /**
     * Constructs new instance.
     *
     * @param upperLimit the upper limit for character sequence length (inclusive)
     */
    public FitIntoSpecification(final int upperLimit) {
        super();
        this.upperLimit = upperLimit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (null != object) && (getClass() == object.getClass())) {
            final FitIntoSpecification other = (FitIntoSpecification) object;
            result = (upperLimit == other.upperLimit);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(upperLimit);
    }

    @Override
    public boolean test(final CharSequence candidate) {
        return (null == candidate) || (candidate.length() <= upperLimit);
    }

}
