package pl.ais.commons.domain.specification;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Predicate;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Verifies the behavior of {@link Predicate} created by {@link Specifications#empty()} method.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@SuppressWarnings("static-method")
public class EmptySpecificationExpectations {

    private static Predicate<Object> specification;

    /**
     * Initializes the specification instance.
     */
    @BeforeClass
    public static void beforeAll() {
        specification = Specifications.empty();
    }

    /**
     * Verifies if the specification is descriptive ({@link #toString()} method provides human readable specification
     * description).
     */
    @Test
    public void shouldBeDescriptive() {
        assertEquals("Specification should be descriptive.", "Is empty", specification.toString());
    }

    /**
     * Verifies if two instances returned by {@link Specifications#empty()} method are equal.
     */
    @Test
    public void returnedInstancesShouldBeEqual() {
        assertEquals("Instances returned should be equal.", specification, Specifications.empty());
    }

    /**
     * Verifies if the specification is satisfied by empty {@link Collection}.
     */
    @Test
    public void shouldBeSatisfiedByEmptyCollection() {
        assertThat("Specification should be satisfied by empty collection.",
            specification.test(Collections.emptyList()));
    }

    /**
     * Verifies if the specification is satisfied by empty {@link Map}.
     */
    @Test
    public void shouldBeSatisfiedByEmptyMap() {
        assertThat("Specification should be satisfied by empty map.",
            specification.test(Collections.emptyMap()));
    }

    /**
     * Verifies if the specification is satisfied by empty {@link String}.
     */
    @Test
    public void shouldBeSatisfiedByEmptyString() {
        assertThat("Specification should be satisfied by empty string.", specification.test(""));
    }

    /**
     * Verifies if the specification is not satisfied by not empty {@link Collection}.
     */
    @Test
    public void shouldntBeSatisfiedByNotEmptyCollection() {
        assertFalse("Specification shouldn't be satisfied by not empty collection.",
            specification.test(Collections.singleton("")));
    }

    /**
     * Verifies if the specification is not satisfied by not empty {@link Map}.
     */
    @Test
    public void shouldntBeSatisfiedByNotEmptyMap() {
        assertFalse("Specification shouldn't be satisfied by not empty map.",
            specification.test(Collections.singletonMap("key", "value")));
    }

    /**
     * Verifies if the specification is not satisfied by not empty {@link String}.
     */
    @Test
    public void shouldntBeSatisfiedByNotEmptyString() {
        assertFalse("Specification shouldn't be satisfied by not empty string.",
            specification.test("not empty string"));
    }

}
