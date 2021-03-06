package pl.ais.commons.domain.specification.simple;

import javax.annotation.concurrent.Immutable;
import java.util.function.Predicate;

/**
 * Specification implementation which is satisfied only if provided candidate is {@code null}.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.2.2
 */
@Immutable
public final class NullValueSpecification<C> implements Predicate<C> {

    @SuppressWarnings("rawtypes")
    private static final NullValueSpecification INSTANCE = new NullValueSpecification<>();

    /**
     * Constructs new instance.
     */
    private NullValueSpecification() {
        super();
    }

    /**
     * @param <T> determines the type of candidate
     * @return shared instance of {@link NullValueSpecification} predicate
     */
    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> nullValue() {
        return (Predicate<T>) INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(final C candidate) {
        return null == candidate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Is null";
    }

}
