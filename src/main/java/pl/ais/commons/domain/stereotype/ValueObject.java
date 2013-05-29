package pl.ais.commons.domain.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.concurrent.Immutable;

/**
 * Indicates that an annotated class is a Value Object.
 *
 * @see <a href="http://martinfowler.com/eaaCatalog/valueObject.html">Value Object (Patterns of Enterprise Application Architecture)</a>
 * @see <a href="http://en.wikipedia.org/wiki/Value_object">Value Object (Wikipedia)</a>
 *
 * @author Warlock, AIS.PL
 * @since 1.0.2
 */
@Documented
@Immutable
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValueObject {

    // Empty by design ...

}
