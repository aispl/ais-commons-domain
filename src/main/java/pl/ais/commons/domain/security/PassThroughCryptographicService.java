package pl.ais.commons.domain.security;

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.ais.commons.domain.stereotype.DomainService;

/**
 * Pass-through implementation of cryptographic service.
 *
 * <p>
 *     This implementation in fact doesn't perform any encryption. Returned {@link DecryptableValue} holds
 *     the unencrypted data passed through.
 * </p>
 *
 * @author Warlock, AIS.PL
 * @since 1.0.2
 */
@ThreadSafe
@DomainService
public final class PassThroughCryptographicService implements CryptographicService<String> {

    private final String charsetName;

    /**
     * Constructs new instance using default charset of this JVM for the string conversions.
     */
    public PassThroughCryptographicService() {
        this(Charset.defaultCharset());
    }

    /**
     * Constructs new instance.
     *
     * @param charset the charset which should be used for the string conversions
     */
    public PassThroughCryptographicService(@Nonnull final Charset charset) {
        super();
        if (null == charset) {
            throw new IllegalArgumentException("Charset cannot be null.");
        }
        this.charsetName = charset.name();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public String decrypt(@Nonnull final DecryptableValue<String> value) {
        if (null == value) {
            throw new IllegalArgumentException();
        }
        return new String(value.getEncryptedValue(), Charset.forName(charsetName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public DecryptableValue<String> encrypt(@Nonnull final String value) {
        if (null == value) {
            throw new IllegalArgumentException();
        }
        return new DecryptableValue<>(this, value.getBytes(Charset.forName(charsetName)));
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (null != object) && (getClass() == object.getClass())) {
            final PassThroughCryptographicService other = (PassThroughCryptographicService) object;
            result = charsetName.equals(other.charsetName);
        }
        return result;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(charsetName).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("charsetName", charsetName).build();
    }

}
