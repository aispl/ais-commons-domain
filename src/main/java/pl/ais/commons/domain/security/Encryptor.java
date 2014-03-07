package pl.ais.commons.domain.security;

import com.google.common.base.Function;

/**
 * Defines the API contract for encryptor.
 *
 * @param <T> defines the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
public interface Encryptor<T> extends Function<T, DecryptableValue<T>> {

    // Empty by design ...

}
