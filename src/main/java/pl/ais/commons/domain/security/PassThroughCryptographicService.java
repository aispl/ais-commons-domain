package pl.ais.commons.domain.security;

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

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
@ThreadSafe
@DomainService
public final class PassThroughCryptographicService extends CryptographicServiceSupport<String> {

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

    private PassThroughCryptographicService(final String charsetName) {
        super(new Decryptor<String>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public String apply(final DecryptableValue<String> value) {
                return new String(value.getEncryptedValue(), Charset.forName(charsetName));
            }

        }, new Encryptor<String>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public DecryptableValue<String> apply(final String value) {
                return new DecryptableValue<String>() {

                    @Override
                    public String decrypt() {
                        return value;
                    }

                    @Override
                    public byte[] getEncryptedValue() {
                        return value.getBytes(Charset.forName(charsetName));
                    }
                };
            }

        });
    }

}
