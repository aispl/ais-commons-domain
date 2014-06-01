package pl.ais.commons.domain.security.crypto;

import static junit.framework.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

/**
 * Verifies {@link Cipherer} expectations.
 *
 * @author Warlock, AIS.PL
 * @since 1.1
 */
public class CiphererExpectations {

    private static SecretKey getSecretKey() {
        return new SecretKeySpec("AdenosineTriphos".getBytes(), "AES");
    }

    private static final byte[] randomCIV(final int size) {
        final byte[] civ = new byte[size];
        new SecureRandom().nextBytes(civ);
        return civ;
    }

    /**
     * @throws NoSuchAlgorithmException
     */
    @SuppressWarnings("static-method")
    @Test
    public void shouldBeAbleToDecryptDecipherableValue() throws NoSuchAlgorithmException {

        // Given value encrypted by the cipherer, ...
        final String unencrypted = "Adenosine diphosphate";
        final Cipherer cipherer = new Cipherer("AES/CBC/PKCS5Padding", getSecretKey(), new IvParameterSpec(
            randomCIV(16)));
        final DecipherableValue encrypted = cipherer.encrypt(unencrypted.getBytes());

        // ... when we decrypt the value, ...
        final String decrypted = new String(encrypted.decrypt());

        // ... decrypted value should be equal to unencrypted.
        assertEquals("Decrypted value should be equal to unencrypted.", unencrypted, decrypted);
    }
}
