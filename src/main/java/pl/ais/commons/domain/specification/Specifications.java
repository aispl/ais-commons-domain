package pl.ais.commons.domain.specification;

import pl.ais.commons.domain.specification.mail.ValidEmailSpecification;
import pl.ais.commons.domain.specification.simple.AfterSpecification;
import pl.ais.commons.domain.specification.simple.BeforeSpecification;
import pl.ais.commons.domain.specification.simple.EmptySpecification;
import pl.ais.commons.domain.specification.simple.FitIntoSpecification;
import pl.ais.commons.domain.specification.simple.NullValueSpecification;
import pl.ais.commons.domain.specification.simple.RegexSpecification;

import java.util.function.Predicate;

/**
 * Provides set of useful {@link Predicate} implementations.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@SuppressWarnings({"PMD.AvoidFieldNameMatchingMethodName", "PMD.TooManyMethods"})
public final class Specifications {

    private static final Predicate<CharSequence> NOT_BLANK = blank().negate();

    /**
     * Constructs new instance.
     */
    private Specifications() {
        super();
    }

    /**
     * @param bound the bound
     * @return specification satisfied only if candidate is after predefined bound
     */
    public static <T extends Comparable<? super T>> Predicate<T> after(final T bound) {
        return new AfterSpecification<>(bound);
    }

    /**
     * @return specification satisfied by any candidate
     */
    public static <T> Predicate<T> always() {
        return candidate -> true;
    }

    /**
     * @param bound the bound
     * @return specification satisfied only if candidate is before predefined bound
     */
    public static <T extends Comparable<? super T>> Predicate<T> before(final T bound) {
        return new BeforeSpecification<>(bound);
    }

    /**
     * @return specification satisfied by character sequences being {@code null}, empty or holding whitespaces only
     */
    public static <T extends CharSequence> Predicate<T> blank() {
        return candidate -> (null == candidate) || candidate.chars().allMatch(Character::isWhitespace);
    }

    /**
     * @return specification satisfied only if provided candidate is empty (applicable to Collection, Map or String)
     */
    public static <T> Predicate<T> empty() {
        return EmptySpecification.empty();
    }

    /**
     * @param upperLimit the upper limit for character sequence length (inclusive)
     * @return specification satisfied by character sequences having length less or equal to given upper limit
     */
    @SuppressWarnings("unchecked")
    public static <T extends CharSequence> Predicate<T> fitInto(final int upperLimit) {
        return (Predicate<T>) new FitIntoSpecification(upperLimit);
    }

    /**
     * @param value the value
     * @return specification satisfied by candidates, which are equal to predefined value
     */
    public static <T> Predicate<T> isEqual(final T value) {
        return Predicate.isEqual(value);
    }

    /**
     * @param regex the regular expression
     * @return specification satisfied by candidates, which matches given regular expression
     */
    @SuppressWarnings("unchecked")
    public static <T extends CharSequence> Predicate<T> matches(final String regex) {
        return (Predicate<T>) new RegexSpecification(regex);
    }

    /**
     * @return specification which will never be satisfied
     */
    public static <T> Predicate<T> never() {
        return candidate -> false;
    }

    /**
     * @return specification satisfied by character sequences holding at least one non-whitespace character
     */
    public static <T extends CharSequence> Predicate<T> notBlank() {
        return (Predicate<T>) NOT_BLANK;
    }

    /**
     * @return specification satisfied by candidates not being {@code null}
     */
    public static <T> Predicate<T> notNull() {
        return candidate -> null != candidate;
    }

    /**
     * @return specification satisfied by candidates being {@code null}
     */
    public static <T> Predicate<T> nullValue() {
        return NullValueSpecification.nullValue();
    }

    /**
     * @return specification satisfied if provided candidate is valid email address.
     */
    public static <T extends CharSequence> Predicate<T> validEmail() {
        return ValidEmailSpecification.INSTANCE;
    }

}
