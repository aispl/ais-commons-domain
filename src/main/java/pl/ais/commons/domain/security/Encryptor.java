package pl.ais.commons.domain.security;

import javax.annotation.Nullable;

/**
 * Defines the API contract for encryptor.
 *
 * @param <T> the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
public interface Encryptor<T> {

    /**
     * Encrypts given value.
     *
     * @param value value to be encrypted
     * @return encrypted value
     */
    @Nullable
    DecryptableValue<T> encrypt(@Nullable T value);

}
