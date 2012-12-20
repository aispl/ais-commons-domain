package pl.ais.commons.domain.specification.composite;

import pl.ais.commons.domain.specification.Specification;

/**
 * Composite specification being negation of other specification.
 *
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public class NotSpecification<T> extends AbstractCompositeSpecification<T> {

    private final Specification<T> specification;

    /**
     * Constructs new instance.
     *
     * @param specification specification for which we are creating negation
     */
    public NotSpecification(final Specification<T> specification) {
        this.specification = specification;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T candidate) {
        return !specification.isSatisfiedBy(candidate);
    }

}
