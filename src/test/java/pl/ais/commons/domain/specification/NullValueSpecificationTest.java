package pl.ais.commons.domain.specification;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Verifies the behavior of {@link Specification} created by {@link Specifications#nullValue()}.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public class NullValueSpecificationTest {

    private static Specification<Object> specification;

    /**
     * Initializes the specification instance.
     */
    @BeforeClass
    public static void beforeAll() {
        specification = Specifications.nullValue();
    }

    /**
     * Verifies if the specification is satisfied by null value.
     */
    @Test
    public void shouldBeSatisfiedByNullValue() {
        assertThat("Specification should be satisfied by null value.", specification.isSatisfiedBy(null));
    }

    /**
     * Verifies if the specification is not satisfied by non-null value.
     */
    @Test
    public void shouldntBeSatisfiedByNonNullValue() {
        assertThat("Specification shouldn't be satisfied by non-null value.", false == specification.isSatisfiedBy(""));
    }
}
