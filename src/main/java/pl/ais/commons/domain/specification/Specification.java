package pl.ais.commons.domain.specification;

import javax.annotation.Nullable;

/**
 * Defines the API contract for the Specification.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Specification_pattern">Specification Pattern</a>
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public interface Specification<C> {

    /**
     * Verifies if this specification is matched by the candidate.
     *
     * @param <T> determines the upper bound for the candidate type
     * @param candidate the candidate to verify
     * @return {@code true} if this specification is matched by the candidate, {@code false} otherwise
     */
    <T extends C> boolean isSatisfiedBy(@Nullable T candidate);

}
