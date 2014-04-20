package tdc2014.testabilidade;

import java.time.Clock;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Past;

/**
 * Versão testável da classe {@link PastValidatorForLocalDate}. Servirá como modelo para o live coding.
 */
public class PastValidatorForLocalDateTestable implements ConstraintValidator<Past, LocalDate> {

    private final Clock clock;

    public PastValidatorForLocalDateTestable() {
        this(Clock.systemDefaultZone());
    }

    PastValidatorForLocalDateTestable(final Clock clock) {
        this.clock = clock;
    }

    @Override
    public void initialize(Past constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext cvc) {
        //null values are valid
        if (date == null) {
            return true;
        }
        return date.isBefore(LocalDate.now(clock));
    }
}
