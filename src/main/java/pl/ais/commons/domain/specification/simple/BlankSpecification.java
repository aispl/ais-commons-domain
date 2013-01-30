package pl.ais.commons.domain.specification.simple;

import pl.ais.commons.domain.specification.Specification;

/**
 * {@link Specification} implementation which is satisfied only if character sequence is {@code null}, empty
 * or holds whitespaces only.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public final class BlankSpecification implements Specification<CharSequence> {

    /**
     * Defines singleton instance of {@link BlankSpecification}.
     */
    public static final Specification<CharSequence> INSTANCE = new BlankSpecification();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final CharSequence candidate) {
        final int length;
        boolean result = true;
        if ((null != candidate) && (0 < (length = candidate.length()))) {
            for (int i = 0; i < length; i++) {
                if (false == Character.isWhitespace(candidate.charAt(i))) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
