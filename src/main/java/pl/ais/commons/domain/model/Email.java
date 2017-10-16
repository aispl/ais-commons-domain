package pl.ais.commons.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Email.
 *
 * @author Warlock
 * @since 1.2.2
 */
@Immutable
public final class Email implements Comparable<Email>, Serializable {

    private static final Comparator<Email> COMPARATOR = Comparator.comparing(Email::toString);

    private static final long serialVersionUID = 3904955193571949632L;

    private final InternetAddress emailAddress;

    /**
     * Constructs new instance.
     *
     * @param address the email address in RFC822 format
     * @throws AddressException if provided email address cannot be parsed correctly
     */
    private Email(@Nonnull final String address) throws AddressException {
        super();

        // Validate constructor requirements, ...
        Objects.requireNonNull(address, "Email address is required.");

        // ... and initialize this instance fields.
        emailAddress = new InternetAddress(address);
        emailAddress.validate();
    }

    /**
     * @param address the email address in RFC822 format
     * @return an {@code Email} instance representing the specified {@code address}
     * @throws AddressException if provided email address cannot be parsed correctly
     */
    @JsonCreator
    @SuppressWarnings("PMD.NullAssignment")
    public static Email valueOf(@Nullable final String address) throws AddressException {
        return (null == address) || address.trim().isEmpty() ? null : new Email(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Email other) {
        return COMPARATOR.compare(this, other);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (object instanceof Email)) {
            final Email other = (Email) object;
            result = Objects.equals(emailAddress, other.emailAddress);
        }
        return result;
    }

    /**
     * @return a hash code value for the email
     */
    @Override
    public int hashCode() {
        return emailAddress.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @JsonValue
    @Override
    public String toString() {
        return emailAddress.toString();
    }

}
