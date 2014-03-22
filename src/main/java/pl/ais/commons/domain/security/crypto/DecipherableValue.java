package pl.ais.commons.domain.security.crypto;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

import javax.annotation.Nonnull;

import pl.ais.commons.domain.security.DecryptableValue;

/**
 * Decipherable value.
 *
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class DecipherableValue implements DecryptableValue<byte[]> {

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
        this.encryptedValue = Arrays.copyOf(encryptedValue, encryptedValue.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] decrypt() {
        return decipherer.withParams(params).apply(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getEncryptedValue() {
        return Arrays.copyOf(encryptedValue, encryptedValue.length);
    }

}
