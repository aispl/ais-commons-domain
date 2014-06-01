package pl.ais.commons.domain.security.crypto;

import static com.google.common.base.Objects.toStringHelper;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.crypto.Cipher;

import pl.ais.commons.domain.security.Encryptor;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

/**
 * {@link Encryptor} implementation based on Java Cryptography Architecture (JCA).
 *
 * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec.html">Java Cryptography Architecture (JCA)</a>
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class Cipherer implements Encryptor<byte[]> {

    private final Key key;

    private final Optional<AlgorithmParameterSpec> params;

    private final String transformation;

    /**
     * Constructs new instance.
     *
     * @param transformation the name of the transformation.
     *        See the Cipher section in the <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher">Java Cryptography Architecture Standard Algorithm Name Documentation</a>
     *        for information about standard transformation names.
     * @param key the encryption key
     */
    public Cipherer(@Nonnull final String transformation, @Nonnull final Key key) {
        this(transformation, key, Optional.<AlgorithmParameterSpec> absent());
    }

    /**
     * Constructs new instance.
     *
     * @param transformation the name of the transformation.
     *        See the Cipher section in the <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher">Java Cryptography Architecture Standard Algorithm Name Documentation</a>
     *        for information about standard transformation names.
     * @param key the encryption key
     * @param params the algorithm parameters
     */
    public Cipherer(@Nonnull final String transformation, @Nonnull final Key key,
        @Nonnull final AlgorithmParameterSpec params) {
        this(transformation, key, Optional.of(params));
    }

    private Cipherer(@Nonnull final String transformation, @Nonnull final Key key,
        @Nonnull final Optional<AlgorithmParameterSpec> params) {

        // Verify constructor requirements, ...
        Preconditions.checkNotNull(transformation, "Transformation is required.");
        Preconditions.checkNotNull(key, "Key is required.");
        Preconditions.checkNotNull(params, "Params are required");

        // ... and initialize this instance fields.
        this.key = key;
        this.params = params;
        this.transformation = transformation;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public DecipherableValue encrypt(@Nullable final byte[] input) {
        final DecipherableValue result;
        if (null == input) {
            result = null;
        } else {
            final Cipher cipher;
            try {
                cipher = Cipher.getInstance(transformation);
                if (params.isPresent()) {
                    cipher.init(Cipher.ENCRYPT_MODE, key, params.get());
                } else {
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                }
                return new DecipherableValue(new Decipherer(transformation, key), params.orNull(),
                    cipher.doFinal(input));
            } catch (GeneralSecurityException exception) {
                throw Throwables.propagate(exception);
            }
        }
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (object instanceof Cipherer)) {
            final Cipherer other = (Cipherer) object;
            result = Objects.equals(transformation, other.transformation) && Objects.equals(key, other.key)
                && Objects.equals(params, other.params);
        }
        return result;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(transformation, key, params);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toStringHelper(this).add("transformation", transformation).toString();
    }

}
