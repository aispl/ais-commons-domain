package pl.ais.commons.domain.security;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Objects;

import javax.annotation.Nonnull;

import pl.ais.commons.domain.stereotype.DomainService;

/**
 * Pass-through implementation of cryptographic service.
 *
 * <p>
 *     This implementation in fact doesn't perform any encryption. Returned {@link DecryptableValue} holds
 *     the unencrypted data passed through.
 * </p>
 *
 * @author Warlock, AIS.PL
 * @since 1.0.2
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
@DomainService
public final class PassThroughCryptographicService extends CryptographicServiceSupport<String> {

    private static final class SerializableDecryptor implements Decryptor<String>, Serializable {

        private static final long serialVersionUID = 37593980407136629L;

        private final String charsetName;

        SerializableDecryptor(final String charsetName) {
            super();
            this.charsetName = charsetName;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String apply(final DecryptableValue<String> value) {
            return new String(value.getEncryptedValue(), Charset.forName(charsetName));
        }

        /**
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(final Object object) {
            boolean result = (this == object);
            if (!result && (null != object) && (getClass() == object.getClass())) {
                final SerializableDecryptor other = (SerializableDecryptor) object;
                result = Objects.equals(charsetName, other.charsetName);
            }
            return result;
        }

        /**
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(charsetName);
        }

    }

    /**
     * Constructs new instance using default charset of this JVM for the string conversions.
     */
    public PassThroughCryptographicService() {
        this(Charset.defaultCharset());
    }

    /**
     * Constructs new instance.
     *
     * @param charset the charset which should be used for the string conversions
     */
    public PassThroughCryptographicService(@Nonnull final Charset charset) {
        this(charset.name());
    }

    /**
     * Constructs new instance.
     *
     * @param charsetName the name of the charset which should be used for the string conversions
     */
    public PassThroughCryptographicService(final String charsetName) {
        this(charsetName, new SerializableDecryptor(charsetName));
    }

    /**
     * Constructs new instance.
     *
     * @param charsetName name of the charset which should be used for the string conversions
     */
    private PassThroughCryptographicService(final String charsetName, final Decryptor<String> decryptor) {
        super(decryptor, new Encryptor<String>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public DecryptableValue<String> apply(final String value) {
                return new DefaultDecryptableValue<>(decryptor, value.getBytes(Charset.forName(charsetName)));
            }

        });
    }
}
