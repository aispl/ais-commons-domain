package pl.ais.commons.domain.specification.simple;

import pl.ais.commons.domain.specification.Specification;

/**
 * {@link Specification} implementation which is satisfied only if provided character sequence length is limited
 * to predefined number of characters, or the character sequence is {@code null}.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
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
    public boolean isSatisfiedBy(final CharSequence candidate) {
        return (null == candidate) || (candidate.length() <= upperLimit);
    }

}
