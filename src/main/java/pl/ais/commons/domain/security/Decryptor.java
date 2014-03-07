package pl.ais.commons.domain.security;

import com.google.common.base.Function;

/**
 * Defines the API contract for decryptor.
 *
 * @param <T> defines the type of unencrypted value
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
public interface Decryptor<T> extends Function<DecryptableValue<T>, T> {

    // Empty by design ...

}
