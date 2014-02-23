package pl.ais.commons.domain.security;

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import pl.ais.commons.domain.stereotype.DomainService;

import com.google.common.base.Objects;

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

    /**
     * Identifies the original class version for which it is capable of writing streams and from which it can read.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/platform/serialization/spec/version.html#6678">Type Changes Affecting Serialization</a>
     */
    private static final long serialVersionUID = -6476927894297927385L;

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
        return charsetName.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("charsetName", charsetName).toString();
    }

}
