package pl.ais.commons.domain.specification.composite;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import pl.ais.commons.domain.specification.Specification;

/**
 * Composite specification being disjunction of other specifications.
 *
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public class OrSpecification<T> extends AbstractCompositeSpecification<T> {

    private final Specification<T>[] specifications;

    /**
     * Constructs new instance.
     *
     * @param specifications specifications for which we create disjunction
     */
    @SafeVarargs
    public OrSpecification(final Specification<T>... specifications) {
        this.specifications = specifications;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends Specification<T>> getComponents() {
        return Collections.unmodifiableList(Arrays.asList(specifications));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        boolean result = false;
        for (Specification<T> specification : specifications) {
            if (specification.isSatisfiedBy(candidate)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
