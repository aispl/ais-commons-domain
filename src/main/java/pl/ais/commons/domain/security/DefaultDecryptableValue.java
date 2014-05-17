package pl.ais.commons.domain.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Nonnull;

import pl.ais.commons.domain.stereotype.ValueObject;

import com.google.common.base.Preconditions;

/**
 * Default {@link DecryptableValue} implementation.
 *
 * @param <T> defines the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.0.2
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
@ValueObject
public final class DefaultDecryptableValue<T> implements DecryptableValue<T>, Serializable {

    /**
     * Identifies the original class version for which it is capable of writing streams and from which it can read.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/platform/serialization/spec/version.html#6678">Type Changes Affecting Serialization</a>
     */
    private static final long serialVersionUID = -2322451953123364855L;

    private transient T decryptedValue;

    private final Decryptor<T> decryptor;

    private final byte[] encryptedValue;

    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Constructs new instance.
     *
     * @param decryptor decryptor which will be used to decrypt value
     * @param encryptedValue encrypted value, which will be enclosed by the created instance
     */
    public DefaultDecryptableValue(@Nonnull final Decryptor<T> decryptor,
        @Nonnull final byte[] encryptedValue) {
        super();

        // Validate constructor requirements, ...
        Preconditions.checkNotNull(decryptor, "Decryptor is required.");
        Preconditions.checkNotNull(encryptedValue, "Encrypted value should be provided.");

        // ... and initialize this instance fields.
        this.decryptor = decryptor;
        this.encryptedValue = Arrays.copyOf(encryptedValue, encryptedValue.length);
    }

    /**
     * Decrypts the value.
     *
     * @return decrypted value
     */
    @Nonnull
    @Override
    public T decrypt() {
        lock.lock();
        try {
            if (null == decryptedValue) {
                decryptedValue = decryptor.apply(this);
            }
            return decryptedValue;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (null != object) && (getClass() == object.getClass())) {
            final DefaultDecryptableValue other = (DefaultDecryptableValue) object;
            result = Objects.equals(decryptor, other.decryptor) && Arrays.equals(encryptedValue, other.encryptedValue);
        }
        return result;
    }

    /**
     * @return the encrypted value
     */
    @Nonnull
    @Override
    public byte[] getEncryptedValue() {
        return Arrays.copyOf(encryptedValue, encryptedValue.length);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (31 * (31 + decryptor.hashCode())) + Arrays.hashCode(encryptedValue);
    }

}
