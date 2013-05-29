package pl.ais.commons.domain.security;

import java.io.Serializable;

import javax.annotation.Nonnull;

/**
 * Defines the API contract for the cryptographic service.
 *
 * @param <T> defines the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.0.2
 */
public interface CryptographicService<T> extends Serializable {

    /**
     * Decrypts the value.
     *
     * @param value the value to decrypt
     * @return decrypted value
     */
    @Nonnull
    T decrypt(@Nonnull DecryptableValue<T> value);

    /**
     * Encrypts given value.
     *
     * @param value the value to encrypt
     * @return encrypted value
     */
    @Nonnull
    DecryptableValue<T> encrypt(@Nonnull T value);

}
