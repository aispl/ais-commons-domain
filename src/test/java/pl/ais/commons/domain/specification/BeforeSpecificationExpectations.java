package pl.ais.commons.domain.specification;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.function.Predicate;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Verifies the behavior of {@link Predicate} created using {@link Specifications#before(Comparable)} method.
 *
 * @author Warlock, AIS.PL
 * @since 1.2.2
 */
@SuppressWarnings("static-method")
public class BeforeSpecificationExpectations {

    private static final Integer BOUNDARY = Integer.valueOf(8);

    private static Predicate<Integer> specification;

    /**
     * Initializes the specification instance.
     */
    @BeforeClass
    public static void beforeAll() {
        specification = Specifications.before(BOUNDARY);
    }

    /**
     * Verifies if two instances returned by {@link Specifications#before(Comparable)} method are equal.
     */
    @Test
    public void returnedInstancesShouldBeEqual() {
        assertEquals("Instances returned should be equal.", specification, Specifications.before(BOUNDARY));
    }

    /**
     * Verifies if the specification is descriptive ({@link #toString()} method provides human readable specification
     * description).
     */
    @Test
    public void shouldBeDescriptive() {
        assertEquals("Specification should be descriptive.", "Is less than " + BOUNDARY, specification.toString());
    }

    /**
     * Verifies if the specification is satisfied by value less than predefined bound.
     */
    @Test
    public void shouldBeSatisfiedByLessValue() {
        assertThat("Specification should be satisfied by value less than predefined bound.",
            specification.test(Integer.valueOf(1)));
    }

    /**
     * Verifies if the specification is not satisfied by value equal to predefined bound.
     */
    @Test
    public void shouldntBeSatisfiedByEqualValue() {
        assertFalse("Specification shouldn't be satisfied by value equal to predefined bound.",
            specification.test(Integer.valueOf(10)));
    }

    /**
     * Verifies if the specification is not satisfied by value greater than predefined bound.
     */
    @Test
    public void shouldntBeSatisfiedByGreaterValue() {
        assertFalse("Specification shouldn't be satisfied by value greater than predefined bound.",
            specification.test(Integer.valueOf(12)));
    }
}
