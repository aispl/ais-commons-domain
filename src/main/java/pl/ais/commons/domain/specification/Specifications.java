package pl.ais.commons.domain.specification;

import pl.ais.commons.domain.specification.simple.BlankSpecification;
import pl.ais.commons.domain.specification.simple.FalseSpecification;
import pl.ais.commons.domain.specification.simple.FitIntoSpecification;
import pl.ais.commons.domain.specification.simple.NullSpecification;
import pl.ais.commons.domain.specification.simple.TrueSpecification;

/**
 * Provides set of useful {@link Specification} implementations.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public final class Specifications {

    /**
     * @return specification satisfied by any candidate
     */
    public static final <T> Specification<T> always() {
        return TrueSpecification.INSTANCE;
    }

    /**
     * @return specification satisfied by character sequences being {@code null}, empty or holding whitespaces only
     */
    public static final Specification<CharSequence> blank() {
        return BlankSpecification.INSTANCE;
    }

    /**
     * @param upperLimit the upper limit for character sequence length (inclusive)
     * @return specification satisfied by character sequences having length less or equal to given upper limit
     */
    public static final Specification<CharSequence> fitInto(final int upperLimit) {
        return new FitIntoSpecification(upperLimit);
    }

    /**
     * @return specification which will never be satisfied
     */
    public static final <T> Specification<T> never() {
        return FalseSpecification.INSTANCE;
    }

    /**
     * @return specification satisfied by character sequences holding at least one non-whitespace character
     */
    public static final Specification<CharSequence> notBlank() {
        return BlankSpecification.NEGATION;
    }

    /**
     * @return specification satisfied by candidates not being {@code null}
     */
    public static final <T> Specification<T> notNull() {
        return NullSpecification.NEGATION;
    }

    /**
     * @return specification satisfied by candidates being {@code null}
     */
    public static final <T> Specification<T> nullValue() {
        return NullSpecification.INSTANCE;
    }

    /**
     * Constructs new instance.
     */
    private Specifications() {
        super();
    }

}
