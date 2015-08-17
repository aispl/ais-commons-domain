package pl.ais.commons.domain.specification.simple;

import javax.annotation.concurrent.Immutable;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * {@link Predicate} implementation satisfied when candidate matches predefined regular expression.
 *
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class RegexSpecification implements Predicate<CharSequence> {

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

    @Override
    public boolean test(final CharSequence candidate) {
        return (null != candidate) && pattern.matcher(candidate).matches();
    }

}
