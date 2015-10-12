package pl.ais.commons.domain.specification.simple;

import javax.annotation.concurrent.Immutable;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Specification implementation which is satisfied only if provided candidate is empty.
 *
 * <p>
 * Note, that this specification can be applied to either Collection, Map or String values;
 * candidate having neither of the above types (including {@code null}) will never satisfy this specification.
 * </p>
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class EmptySpecification<C> implements Predicate<C> {

    private static final Predicate INSTANCE = new EmptySpecification();

    /**
     * Constructs new instance.
     */
    private EmptySpecification() {
        super();
    }

    /**
     * @param <T> the type of candidate to be verified by the predicate
     * @return shared instance of {@link EmptySpecification} predicate
     */
    public static <T> Predicate<T> empty() {
        return INSTANCE;
    }

    @Override
    public boolean test(final C candidate) {
        boolean result = false;
        processing:
        {
            if (candidate instanceof Collection) {
                result = ((Collection) candidate).isEmpty();
                break processing;
            }
            if (candidate instanceof Map) {
                result = ((Map) candidate).isEmpty();
                break processing;
            }
            if (candidate instanceof String) {
                result = ((String) candidate).isEmpty();
                break processing;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Is empty";
    }

}
