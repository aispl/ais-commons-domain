package pl.ais.commons.domain.security;

import javax.annotation.Nonnull;

/**
 * Defines the API contract for decryptable value.
 *
 * @param <T> the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.0.2
 */
public interface DecryptableValue<T> {

    /**
     * @return decrypted value
     */
    @Nonnull
    T decrypt();

    /**
     * @return encrypted value
     */
    @Nonnull
    byte[] getEncryptedValue();

}
