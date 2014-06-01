package pl.ais.commons.domain.security;

import static com.google.common.base.Objects.toStringHelper;

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
@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.OverrideBothEqualsAndHashcode"})
@DomainService
public final class PassThroughCryptographicService extends CryptographicServiceSupport<String> implements
    DecryptorProvider<String> {

    private static final class SerializableDecryptor implements Decryptor<String>, Serializable {

        private static final long serialVersionUID = -1730706243212998934L;

        private final String charsetName;

        SerializableDecryptor(final String charsetName) {
            super();
            this.charsetName = charsetName;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @SuppressWarnings("PMD.NullAssignment")
        public String decrypt(final DecryptableValue<String> value) {
            return (null == value) ? null : new String(value.getEncryptedValue(), Charset.forName(charsetName));
        }

        /**
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(final Object object) {
            boolean result = (this == object);
            if (!result && (object instanceof SerializableDecryptor)) {
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
            return charsetName.hashCode();
        }

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return toStringHelper(this).add("charsetName", charsetName).toString();
        }

    }

    private static final class SerializableEncryptor implements Encryptor<String>, Serializable {

        private static final long serialVersionUID = 7202178379823648202L;

        private final String charsetName;

        private final Decryptor<String> decryptor;

        SerializableEncryptor(final String charsetName, final Decryptor<String> decryptor) {
            super();
            this.charsetName = charsetName;
            this.decryptor = decryptor;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @SuppressWarnings("PMD.NullAssignment")
        public DecryptableValue<String> encrypt(final String value) {
            return (null == value) ? null : new DefaultDecryptableValue<>(decryptor, value.getBytes(Charset
                .forName(charsetName)));
        }

        /**
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(final Object object) {
            boolean result = (this == object);
            if (!result && (object instanceof SerializableEncryptor)) {
                final SerializableEncryptor other = (SerializableEncryptor) object;
                result = Objects.equals(charsetName, other.charsetName);
            }
            return result;
        }

        /**
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return charsetName.hashCode();
        }

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return toStringHelper(this).add("charsetName", charsetName).toString();
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
     * @param decryptor to be used as decryption delegate
     * @param encryptor to be used as encryption delegate
     */
    protected PassThroughCryptographicService(final Decryptor<String> decryptor, final Encryptor<String> encryptor) {
        super(decryptor, encryptor);
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
     * @param decryptor decryptor to be used
     */
    private PassThroughCryptographicService(final String charsetName, final Decryptor<String> decryptor) {
        this(decryptor, new SerializableEncryptor(charsetName, decryptor));
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = (this == object);
        if (!result && (object instanceof PassThroughCryptographicService)) {
            result = equivalentOf((PassThroughCryptographicService) object);
        }
        return result;
    }

}
