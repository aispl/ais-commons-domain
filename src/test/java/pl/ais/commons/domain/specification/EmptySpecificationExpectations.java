package pl.ais.commons.domain.specification;

import static junit.framework.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Verifies the behavior of {@link Specification} created by {@link Specifications#empty()} method.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@SuppressWarnings("static-method")
public class EmptySpecificationExpectations {

    private static Specification<Object> specification;

    /**
     * Initializes the specification instance.
     */
    @BeforeClass
    public static void beforeAll() {
        specification = Specifications.empty();
    }

    /**
     * Verifies if the specification is satisfied by empty {@link Collection}.
     */
    @Test
    public void shouldBeSatisfiedByEmptyCollection() {
        assertThat("Specification should be satisfied by empty collection.",
            specification.isSatisfiedBy(Collections.emptyList()));
    }

    /**
     * Verifies if the specification is satisfied by empty {@link Map}.
     */
    @Test
    public void shouldBeSatisfiedByEmptyMap() {
        assertThat("Specification should be satisfied by empty map.",
            specification.isSatisfiedBy(Collections.emptyMap()));
    }

    /**
     * Verifies if the specification is satisfied by empty {@link String}.
     */
    @Test
    public void shouldBeSatisfiedByEmptyString() {
        assertThat("Specification should be satisfied by empty string.", specification.isSatisfiedBy(""));
    }

    /**
     * Verifies if the specification is not satisfied by not empty {@link Collection}.
     */
    @Test
    public void shouldntBeSatisfiedByNotEmptyCollection() {
        assertFalse("Specification shouldn't be satisfied by not empty collection.",
            specification.isSatisfiedBy(Collections.singleton("")));
    }

    /**
     * Verifies if the specification is not satisfied by not empty {@link Map}.
     */
    @Test
    public void shouldntBeSatisfiedByNotEmptyMap() {
        assertFalse("Specification shouldn't be satisfied by not empty map.",
            specification.isSatisfiedBy(Collections.singletonMap("key", "value")));
    }

    /**
     * Verifies if the specification is not satisfied by not empty {@link String}.
     */
    @Test
    public void shouldntBeSatisfiedByNotEmptyString() {
        assertFalse("Specification shouldn't be satisfied by not empty string.",
            specification.isSatisfiedBy("not empty string"));
    }

}
