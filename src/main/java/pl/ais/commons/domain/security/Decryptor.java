package pl.ais.commons.domain.security;

import javax.annotation.Nullable;

/**
 * Defines the API contract for decryptor.
 *
 * @param <T> the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
public interface Decryptor<T> {

    /**
     * Decrypts given value.
     *
     * @param value value to be decrypted
     * @return decrypted value
     */
    @Nullable
    T decrypt(@Nullable DecryptableValue<T> value);

}
