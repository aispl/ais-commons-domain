package pl.ais.commons.domain.specification;

import pl.ais.commons.domain.specification.composite.NotSpecification;
import pl.ais.commons.domain.specification.mail.ValidEmailSpecification;
import pl.ais.commons.domain.specification.simple.AfterSpecification;
import pl.ais.commons.domain.specification.simple.BeforeSpecification;
import pl.ais.commons.domain.specification.simple.BlankSpecification;
import pl.ais.commons.domain.specification.simple.EmptySpecification;
import pl.ais.commons.domain.specification.simple.FitIntoSpecification;
import pl.ais.commons.domain.specification.simple.IsEqualSpecification;
import pl.ais.commons.domain.specification.simple.RegexSpecification;
import pl.ais.commons.domain.specification.simple.TrueSpecification;

/**
 * Provides set of useful {@link Specification} implementations.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public final class Specifications {

    /**
     * @param bound the bound
     * @return specification satisfied only if candidate is after predefined bound
     */
    public static final <T extends Comparable<? super T>> Specification<T> after(final T bound) {
        return new AfterSpecification<>(bound);
    }

    /**
     * @return specification satisfied by any candidate
     */
    public static final <T> Specification<T> always() {
        return TrueSpecification.INSTANCE;
    }

    /**
     * @param bound the bound
     * @return specification satisfied only if candidate is before predefined bound
     */
    public static final <T extends Comparable<? super T>> Specification<T> before(final T bound) {
        return new BeforeSpecification<>(bound);
    }

    /**
     * @return specification satisfied by character sequences being {@code null}, empty or holding whitespaces only
     */
    public static final <T extends CharSequence> Specification<T> blank() {
        return BlankSpecification.INSTANCE;
    }

    /**
     * @return specification satisfied only if provided candidate is empty (applicable to Collection, Map or String)
     */
    public static final <T> Specification<T> empty() {
        return EmptySpecification.INSTANCE;
    }

    /**
     * @param upperLimit the upper limit for character sequence length (inclusive)
     * @return specification satisfied by character sequences having length less or equal to given upper limit
     */
    @SuppressWarnings("unchecked")
    public static final <T extends CharSequence> Specification<T> fitInto(final int upperLimit) {
        return (Specification<T>) new FitIntoSpecification(upperLimit);
    }

    /**
     * @param value the value
     * @return specification satisfied by candidates, which are equal to predefined value
     */
    public static final <T> Specification<T> isEqual(final T value) {
        return new IsEqualSpecification<>(value);
    }

    /**
     * @param regex the regular expression
     * @return specification satisfied by candidates, which matches given regular expression
     */
    @SuppressWarnings("unchecked")
    public static final <T extends CharSequence> Specification<T> matches(final String regex) {
        return (Specification<T>) new RegexSpecification(regex);
    }

    /**
     * @return specification which will never be satisfied
     */
    public static final <T> Specification<T> never() {
        return new NotSpecification<>(TrueSpecification.INSTANCE);
    }

    /**
     * @return specification satisfied by character sequences holding at least one non-whitespace character
     */
    public static final <T extends CharSequence> Specification<T> notBlank() {
        return new NotSpecification<>(BlankSpecification.INSTANCE);
    }

    /**
     * @return specification satisfied by candidates not being {@code null}
     */
    public static final <T> Specification<T> notNull() {
        return new NotSpecification<>(new IsEqualSpecification<T>(null));
    }

    /**
     * @return specification satisfied by candidates being {@code null}
     */
    public static final <T> Specification<T> nullValue() {
        return new IsEqualSpecification<>(null);
    }

    /**
     * @return specification satisfied if provided candidate is valid email address.
     */
    public static final <T extends CharSequence> Specification<T> validEmail() {
        return ValidEmailSpecification.INSTANCE;
    }

    /**
     * Constructs new instance.
     */
    private Specifications() {
        super();
    }

}
