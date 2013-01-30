package pl.ais.commons.domain.specification.simple;

import java.util.regex.Pattern;

import pl.ais.commons.domain.specification.Specification;

/**
 * {@link Specification} implementation satisfied when candidate matches predefined regular expression.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
public class RegexSpecification implements Specification<CharSequence> {

    private final Pattern pattern;

    /**
     * Constructs new instance.
     *
     * @param regex regular expression
     */
    public RegexSpecification(final String regex) {
        super();
        this.pattern = Pattern.compile(regex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final CharSequence candidate) {
        return (null != candidate) && pattern.matcher(candidate).matches();
    }
}
