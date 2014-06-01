package pl.ais.commons.domain.security;

import static com.google.common.base.Objects.toStringHelper;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

/**
 * Base class for cryptographic services.
 *
 * <p>
 *   This implementation delegates the encryption/decryption work to predefined {@link Encryptor} / {@link Decryptor}.
 * </p>
 * <p>
 *   Although this class is not serializable itself, subclasses of it can be made serializable using
 *   <em>Serialization Proxy Pattern</em> - see: <em>Effective Java, Second Edition</em> by Joshua Bloch
 *   (ISBN-13: 978-0-321-35668-0)
 * </p>
 *
 * @param <T> defines the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class CryptographicServiceSupport<T> implements Decryptor<T>, Encryptor<T> {

    private final Decryptor<T> decryptor;

    private final Encryptor<T> encryptor;

    /**
     * Constructs new instance.
     *
     * @param decryptor to be used as decryption delegate
     * @param encryptor to be used as encryption delegate
     */
    protected CryptographicServiceSupport(@Nonnull final Decryptor<T> decryptor, @Nonnull final Encryptor<T> encryptor) {
        super();

        // Validate constructor requirements, ...
        Preconditions.checkNotNull(decryptor, "Decryptor is required.");
        Preconditions.checkNotNull(encryptor, "Encryptor is required.");

        // ... and initialize this instance fields.
        this.decryptor = decryptor;
        this.encryptor = encryptor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public T decrypt(@Nullable final DecryptableValue<T> decryptable) {
        return decryptor.decrypt(decryptable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public DecryptableValue<T> encrypt(@Nullable final T encryptable) {
        return encryptor.encrypt(encryptable);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (object instanceof CryptographicServiceSupport)) {
            final CryptographicServiceSupport other = (CryptographicServiceSupport) object;
            result = Objects.equals(decryptor, other.decryptor) && Objects.equals(encryptor, other.encryptor);
        }
        return result;
    }

    /**
     * @return the decryptor
     */
    public Decryptor<T> getDecryptor() {
        return decryptor;
    }

    /**
     * @return the encryptor
     */
    public Encryptor<T> getEncryptor() {
        return encryptor;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(decryptor, encryptor);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toStringHelper(this).add("decryptor", decryptor).add("encryptor", encryptor).toString();
    }

}
