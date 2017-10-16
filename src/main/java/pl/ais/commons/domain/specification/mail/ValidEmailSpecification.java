package pl.ais.commons.domain.specification.mail;

import javax.annotation.concurrent.Immutable;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.function.Predicate;

/**
 * {@link Predicate} implementation which is satisfied if provided candidate is a char sequence holding valid email
 * address.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class ValidEmailSpecification implements Predicate<CharSequence> {

    private static final Predicate INSTANCE = new ValidEmailSpecification();

    /**
     * @return shared instance of {@link ValidEmailSpecification}
     */
    public static <T extends CharSequence> Predicate<T> validEmail() {
        return (Predicate<T>) INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public boolean test(final CharSequence candidate) {
        boolean result = false;
        if (null != candidate) {
            try {
                new InternetAddress(candidate.toString()).validate();
                result = true;
            } catch (final AddressException exception) {
                // Ignore ...
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Valid email";
    }

}
