package pl.ais.commons.domain.specification.composite;

import pl.ais.commons.domain.specification.Specification;

/**
 * Specification being negation of other specification.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class NotSpecification<C> extends AbstractCompositeSpecification<C> {

    private final Specification<C> specification;

    /**
     * Constructs new instance.
     *
     * @param specification specification for which we are creating negation
     */
    public NotSpecification(final Specification<C> specification) {
        super();
        this.specification = specification;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Specification<C>[] getComponents() {
        return new Specification[] {specification};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> boolean isSatisfiedBy(final T candidate) {
        return !specification.isSatisfiedBy(candidate);
    }

}
