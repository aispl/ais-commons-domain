package pl.ais.commons.domain.specification.simple;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.ais.commons.domain.specification.Specification;

/**
 * {@link Specification} implementation which is satisfied only if provided character sequence length is limited
 * to predefined number of characters, or the character sequence is {@code null}.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class FitIntoSpecification implements Specification<CharSequence> {

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
        return new HashCodeBuilder().append(upperLimit).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CharSequence> boolean isSatisfiedBy(final T candidate) {
        return (null == candidate) || (candidate.length() <= upperLimit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("upperLimit", upperLimit).build();
    }

}
