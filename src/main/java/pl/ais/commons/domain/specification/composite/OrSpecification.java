package pl.ais.commons.domain.specification.composite;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Composite specification being disjunction of other specifications.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class OrSpecification<C> extends AbstractCompositeSpecification<C> {

    private final Specification<C>[] specifications;

    /**
     * Constructs new instance.
     *
     * @param first first specification for which we create disjunction
     * @param others other specifications for which we create disjunction
     */
    @SuppressWarnings("unchecked")
    @SafeVarargs
    public OrSpecification(final Specification<C> first, final Specification<C>... others) {
        super();
        this.specifications = (Specification<C>[]) Array.newInstance(first.getClass(), others.length + 1);
        this.specifications[0] = first;
        System.arraycopy(others, 0, this.specifications, 1, others.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<C>[] getComponents() {
        return Arrays.copyOf(specifications, specifications.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> boolean isSatisfiedBy(final T candidate) {
        boolean result = false;
        for (final Specification<C> specification : specifications) {
            if (specification.isSatisfiedBy(candidate)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
