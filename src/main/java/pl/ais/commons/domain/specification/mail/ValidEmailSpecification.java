package pl.ais.commons.domain.specification.mail;

import javax.annotation.concurrent.Immutable;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import pl.ais.commons.domain.specification.Specification;

/**
 * {@link Specification} implementation which is satisfied if provided candidate is valid email address.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class ValidEmailSpecification implements Specification<CharSequence> {

    /**
     * Defines singleton instance of {@link ValidEmailSpecification}.
     */
    @SuppressWarnings("rawtypes")
    public static final Specification INSTANCE = new ValidEmailSpecification();

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public <T extends CharSequence> boolean isSatisfiedBy(final T candidate) {
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
