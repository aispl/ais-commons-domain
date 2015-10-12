package pl.ais.commons.domain.security;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.springframework.util.SerializationUtils.deserialize;
import static org.springframework.util.SerializationUtils.serialize;

/**
 * Verifies {@link DefaultDecryptableValue} expectations.
 *
 * @author Warlock, AIS.PL
 * @since 1.1.1
 */
public class DefaultDecryptableValueExpectations {

    /**
     * Verifies if {@link DefaultDecryptableValue} is serializable, if applicable decryptor is serializable too.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void shouldBeSerializableWhenUsingSerializableDecryptor() {
        final String unencrypted = "Adenosine triphosphate";
        final PassThroughCryptographicService service = new PassThroughCryptographicService();
        final DefaultDecryptableValue<String> encrypted = DefaultDecryptableValue.factory(service.getDecryptor())
            .decryptableValue(service.encrypt(unencrypted).getEncryptedValue());
        final DefaultDecryptableValue<String> deserialized = (DefaultDecryptableValue<String>) deserialize(serialize(encrypted));
        assertTrue("Deserialized instance differs from initial one.", (encrypted.hashCode() == deserialized.hashCode())
            && encrypted.equals(deserialized));
    }
}
