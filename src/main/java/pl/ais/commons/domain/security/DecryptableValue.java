package pl.ais.commons.domain.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Nonnull;

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

    /**
     * Identifies the original class version for which it is capable of writing streams and from which it can read.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/platform/serialization/spec/version.html#6678">Type Changes Affecting Serialization</a>
     */
    private static final long serialVersionUID = 42804407628452076L;

    private transient T decryptedValue;

    private final CryptographicService<T> decryptor;

    private final byte[] encryptedValue;

    private final ReentrantLock lock = new ReentrantLock();

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
        lock.lock();
        try {
            if (null == decryptedValue) {
                decryptedValue = decryptor.decrypt(this);
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
            final DecryptableValue other = (DecryptableValue) object;
            result = Objects.equals(decryptor, other.decryptor) && Objects.equals(encryptedValue, other.encryptedValue);
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
        return Objects.hash(decryptor, encryptedValue);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this).add("decryptor", decryptor).toString();
    }

}
