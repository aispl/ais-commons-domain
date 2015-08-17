package pl.ais.commons.domain.specification.mail;

import javax.annotation.concurrent.Immutable;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.function.Predicate;

/**
 * {@link Predicate} implementation which is satisfied if provided candidate is valid email address.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class ValidEmailSpecification implements Predicate<CharSequence> {

    /**
     * Defines singleton instance of {@link ValidEmailSpecification}.
     */
    @SuppressWarnings("rawtypes")
    public static final Predicate INSTANCE = new ValidEmailSpecification();

    @Override
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

}
