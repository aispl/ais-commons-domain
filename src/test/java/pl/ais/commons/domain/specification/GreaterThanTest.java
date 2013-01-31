package pl.ais.commons.domain.specification;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Verifies the behavior of {@link Specification} created using {@link Specifications#after(Comparable)} method.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public class GreaterThanTest {

    private static Specification<Integer> specification;

    /**
     * Initializes the specification instance.
     */
    @BeforeClass
    public static void beforeAll() {
        specification = Specifications.after(Integer.valueOf(2));
    }

    /**
     * Verifies if the specification is satisfied by value greater than predefined bound.
     */
    @Test
    public void shouldBeSatisfiedByGreaterValue() {
        assertThat("Specification should be satisfied by value greater than predefined bound.",
            specification.isSatisfiedBy(Integer.valueOf(10)));
    }

    /**
     * Verifies if the specification is not satisfied by value equal to predefined bound.
     */
    @Test
    public void shouldntBeSatisfiedByEqualValue() {
        assertThat("Specification shouldn't be satisfied by value equal to predefined bound.",
            false == specification.isSatisfiedBy(Integer.valueOf(2)));
    }

    /**
     * Verifies if the specification is not satisfied by value less than predefined bound.
     */
    @Test
    public void shouldntBeSatisfiedByLessValue() {
        assertThat("Specification shouldn't be satisfied by value less than predefined bound.",
            false == specification.isSatisfiedBy(Integer.valueOf(1)));
    }
}
