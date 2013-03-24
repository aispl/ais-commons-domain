package pl.ais.commons.domain.specification.composite;

import java.util.Arrays;

import javax.annotation.concurrent.Immutable;

import pl.ais.commons.domain.specification.Specification;

/**
 * Composite specification being disjunction of other specifications.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class OrSpecification<C> extends AbstractCompositeSpecification<C> {

    private final Specification<C>[] specifications;

    /**
     * Constructs new instance.
     *
     * @param specifications specifications for which we create disjunction
     */
    @SafeVarargs
    public OrSpecification(final Specification<C>... specifications) {
        this.specifications = specifications;
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
        for (Specification<C> specification : specifications) {
            if (specification.isSatisfiedBy(candidate)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
