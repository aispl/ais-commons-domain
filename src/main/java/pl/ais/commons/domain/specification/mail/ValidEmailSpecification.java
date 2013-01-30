package pl.ais.commons.domain.specification.mail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import pl.ais.commons.domain.specification.Specification;

/**
 * {@link Specification} implementation which is satisfied if provided candidate is valid email address.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public final class ValidEmailSpecification implements Specification<String> {

    /**
     * Defines singleton instance of {@link ValidEmailSpecification}.
     */
    public static final Specification<String> INSTANCE = new ValidEmailSpecification();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final String candidate) {
        boolean result = true;
        try {
            new InternetAddress(candidate).validate();
        } catch (final AddressException exception) {
            result = false;
        }
        return result;
    }

}
