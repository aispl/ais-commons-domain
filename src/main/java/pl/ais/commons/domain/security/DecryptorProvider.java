package pl.ais.commons.domain.security;

/**
 * Defines the API contract for {@link Decryptor} provider.
 *
 * @param <T> the type of unencrypted values supported by decryptor
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
public interface DecryptorProvider<T> {

    /**
     * @return decryptor instance (either shared or newly created)
     */
    Decryptor<T> getDecryptor();

}
