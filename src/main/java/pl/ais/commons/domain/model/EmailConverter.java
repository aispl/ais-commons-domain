package pl.ais.commons.domain.model;

import javax.mail.internet.AddressException;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Provides possibility of converting {@link Email} into its {@link String} representation, and back again.
 *
 * @author Warlock, AIS.PL
 * @since 1.2.2
 */
@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    @SuppressWarnings("PMD.NullAssignment")
    public String convertToDatabaseColumn(final Email attribute) {
        return (null == attribute) ? null : attribute.toString();
    }

    @Override
    public Email convertToEntityAttribute(final String data) {
        try {
            return Email.valueOf(data);
        } catch (final AddressException exception) {
            throw new IllegalArgumentException(String.format("Unable to convert '%s' into Email instance.", data), exception);
        }
    }

}
