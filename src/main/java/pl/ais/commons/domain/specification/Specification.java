package pl.ais.commons.domain.specification;

import javax.annotation.Nullable;

/**
 * Defines the API contract for Specification.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Specification_pattern">Specification Pattern</a>
 * @param <T> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public interface Specification<T> {

    /**
     * Verifies if this specification is matched by the candidate.
     *
     * @param candidate the candidate to verify
     * @return {@code true} if this specification is matched by the candidate, {@code false} otherwise
     */
    boolean isSatisfiedBy(@Nullable T candidate);

}
