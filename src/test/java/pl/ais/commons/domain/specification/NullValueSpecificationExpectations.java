package pl.ais.commons.domain.specification;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.function.Predicate;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Verifies the behavior of {@link Predicate} created by {@link Specifications#nullValue()}.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@SuppressWarnings("static-method")
public class NullValueSpecificationExpectations {

    private static Predicate<Object> specification;

    /**
     * Initializes the specification instance.
     */
    @BeforeClass
    public static void beforeAll() {
        specification = Specifications.nullValue();
    }

    /**
     * Verifies if two instances returned by {@link Specifications#nullValue()} method are equal.
     */
    @Test
    public void returnedInstancesShouldBeEqual() {
        assertEquals("Instances returned should be equal.", specification, Specifications.nullValue());
    }

    /**
     * Verifies if the specification is descriptive ({@link #toString()} method provides human readable specification
     * description).
     */
    @Test
    public void shouldBeDescriptive() {
        assertEquals("Specification should be descriptive.", "Is null", specification.toString());
    }

    /**
     * Verifies if the specification is satisfied by null value.
     */
    @Test
    public void shouldBeSatisfiedByNullValue() {
        assertThat("Specification should be satisfied by null value.", specification.test(null));
    }

    /**
     * Verifies if the specification is not satisfied by non-null value.
     */
    @Test
    public void shouldntBeSatisfiedByNonNullValue() {
        assertFalse("Specification shouldn't be satisfied by non-null value.", specification.test(""));
    }

}
