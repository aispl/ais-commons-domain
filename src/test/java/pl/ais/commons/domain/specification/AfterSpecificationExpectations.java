package pl.ais.commons.domain.specification;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.function.Predicate;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Verifies the behavior of {@link Predicate} created using {@link Specifications#after(Comparable)} method.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@SuppressWarnings("static-method")
public class AfterSpecificationExpectations {

    private static final Integer BOUNDARY = Integer.valueOf(2);

    private static Predicate<Integer> specification;

    /**
     * Initializes the specification instance.
     */
    @BeforeClass
    public static void beforeAll() {
        specification = Specifications.after(BOUNDARY);
    }

    /**
     * Verifies if two instances returned by {@link Specifications#after(Comparable)} method are equal.
     */
    @Test
    public void returnedInstancesShouldBeEqual() {
        assertEquals("Instances returned should be equal.", specification, Specifications.after(BOUNDARY));
    }

    /**
     * Verifies if the specification is descriptive ({@link #toString()} method provides human readable specification
     * description).
     */
    @Test
    public void shouldBeDescriptive() {
        assertEquals("Specification should be descriptive.", "Is greater than " + BOUNDARY, specification.toString());
    }

    /**
     * Verifies if the specification is satisfied by value greater than predefined bound.
     */
    @Test
    public void shouldBeSatisfiedByGreaterValue() {
        assertThat("Specification should be satisfied by value greater than predefined bound.",
            specification.test(Integer.valueOf(10)));
    }

    /**
     * Verifies if the specification is not satisfied by value equal to predefined bound.
     */
    @Test
    public void shouldntBeSatisfiedByEqualValue() {
        assertFalse("Specification shouldn't be satisfied by value equal to predefined bound.",
            specification.test(BOUNDARY));
    }

    /**
     * Verifies if the specification is not satisfied by value less than predefined bound.
     */
    @Test
    public void shouldntBeSatisfiedByLessValue() {
        assertFalse("Specification shouldn't be satisfied by value less than predefined bound.",
            specification.test(Integer.valueOf(1)));
    }
}
