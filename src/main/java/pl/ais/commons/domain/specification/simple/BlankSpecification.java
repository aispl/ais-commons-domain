package pl.ais.commons.domain.specification.simple;

import javax.annotation.concurrent.Immutable;

import pl.ais.commons.domain.specification.Specification;

/**
 * {@link Specification} implementation which is satisfied only if character sequence is {@code null}, empty
 * or holds whitespaces only.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class BlankSpecification implements Specification<CharSequence> {

    /**
     * Defines singleton instance of {@link BlankSpecification}.
     */
    @SuppressWarnings("rawtypes")
    public static final Specification INSTANCE = new BlankSpecification();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CharSequence> boolean isSatisfiedBy(final T candidate) {
        boolean result = true;
        if (null != candidate) {
            final int length = candidate.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(candidate.charAt(i))) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
