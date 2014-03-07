package pl.ais.commons.domain.security;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

/**
 * Cryptographic service.
 *
 * <p>
 *   This service delegates the encryption/decryption work to predefined {@link Encryptor} / {@link Decryptor}.
 * </p>
 *
 * @param <T> defines the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class CryptographicServiceSupport<T> {

    private final Decryptor<T> decryptor;

    private final Encryptor<T> encryptor;

    /**
     * Constructs new instance.
     *
     * @param decryptor to be used as decryption delegate
     * @param encryptor to be used as encryption delegate
     */
    public CryptographicServiceSupport(@Nonnull final Decryptor<T> decryptor, @Nonnull final Encryptor<T> encryptor) {

        // Validate constructor requirements, ...
        Preconditions.checkNotNull(decryptor, "Decryptor is required.");
        Preconditions.checkNotNull(encryptor, "Encryptor is required.");

        // ... and initialize this instance fields.
        this.decryptor = decryptor;
        this.encryptor = encryptor;
    }

    /**
     * Decrypts the value.
     *
     * @param decryptable the value to decrypt
     * @return decrypted value
     */
    @Nonnull
    public T decrypt(@Nonnull final DecryptableValue<T> decryptable) {

        // Validate method requirements, ...
        Preconditions.checkNotNull(decryptable, "Decryptable value should be provided.");

        // ... and ask decryptor to do the dirty work.
        return decryptor.apply(decryptable);
    }

    /**
     * Encrypts given value.
     *
     * @param encryptable the value to encrypt
     * @return encrypted value
     */
    @Nonnull
    public DecryptableValue<T> encrypt(@Nonnull final T encryptable) {

        // Validate method requirements, ...
        Preconditions.checkNotNull(encryptable, "Encryptable value should be provided.");

        // ... and ask encryptor to do the dirty work.
        return encryptor.apply(encryptable);
    }

}
