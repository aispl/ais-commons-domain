package pl.ais.commons.domain.security;

import java.io.Serializable;
import java.util.Arrays;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.ais.commons.domain.stereotype.ValueObject;

/**
 * Decryptable value.
 *
 * @param <T> defines the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.0.2
 */
@ValueObject
public final class DecryptableValue<T> implements Serializable {

    private transient T decryptedValue;

    private final CryptographicService<T> decryptor;

    private final byte[] encryptedValue;

    /**
     * Constructs new instance.
     *
     * @param decryptor decryptor which will be used to decrypt value
     * @param encryptedValue encrypted value, which will be enclosed by the created instance
     */
    public DecryptableValue(@Nonnull final CryptographicService<T> decryptor, @Nonnull final byte[] encryptedValue) {
        super();
        if ((null == decryptor) || (null == encryptedValue)) {
            throw new IllegalArgumentException("Both decryptor and encrypted value cannot be null.");
        }
        this.decryptor = decryptor;
        this.encryptedValue = Arrays.copyOf(encryptedValue, encryptedValue.length);
    }

    /**
     * Decrypts the value.
     *
     * @return decrypted value
     */
    @Nonnull
    public T decrypt() {
        if (null == decryptedValue) {
            decryptedValue = decryptor.decrypt(this);
        }
        return decryptedValue;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (null != object) && (getClass() == object.getClass())) {
            final DecryptableValue other = (DecryptableValue) object;
            result = new EqualsBuilder().append(decryptor, other.decryptor)
                .append(encryptedValue, other.encryptedValue).isEquals();
        }
        return result;
    }

    /**
     * @return the encrypted value
     */
    @Nonnull
    public byte[] getEncryptedValue() {
        return Arrays.copyOf(encryptedValue, encryptedValue.length);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(decryptor).append(encryptedValue).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("decryptor", decryptor).build();
    }

}
