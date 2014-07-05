package pl.ais.commons.domain.security;

/**
 * Defines the API contract for {@link DecryptableValue} factory.
 *
 * @param <T> the type of unencrypted value
 * @param <D> the type of decryptable value
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
public interface DecryptableValueFactory<T, D extends DecryptableValue<T>> {

    /**
     * Returns decryptable value built from encrypted value representation.
     *
     * @param representation encrypted value's representation
     * @return decryptable value
     */
    D decryptableValue(byte[] representation);

}
