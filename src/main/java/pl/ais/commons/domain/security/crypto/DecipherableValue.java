package pl.ais.commons.domain.security.crypto;

import static com.google.common.base.Objects.toStringHelper;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;

import javax.annotation.Nonnull;

import pl.ais.commons.domain.security.DecryptableValue;

/**
 * Decipherable value.
 *
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class DecipherableValue implements DecryptableValue<byte[]> {

    private final Decipherer decipherer;

    private final byte[] encryptedValue;

    private final AlgorithmParameterSpec params;

    /**
     * Constructs new instance.
     *
     * @param decipherer decipherer which can be used to decrypt the value
     * @param params the algorithm parameters
     * @param encryptedValue encrypted value
     */
    public DecipherableValue(@Nonnull final Decipherer decipherer, final AlgorithmParameterSpec params,
        @Nonnull final byte[] encryptedValue) {
        this.decipherer = decipherer;
        this.params = params;
        this.encryptedValue = encryptedValue.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] decrypt() {
        return decipherer.withParams(params).decrypt(this);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (object instanceof DecipherableValue)) {
            final DecipherableValue other = (DecipherableValue) object;
            result = Objects.equals(decipherer, other.decipherer) && Objects.equals(params, other.params)
                && Objects.equals(encryptedValue, other.encryptedValue);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getEncryptedValue() {
        return encryptedValue.clone();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(decipherer, params, encryptedValue);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toStringHelper(this).add("decipherer", decipherer).toString();
    }

}
