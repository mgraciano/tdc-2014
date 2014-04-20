package tdc2014.testabilidade;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Past;

/**
 * Suporte a {@link LocalDate} para validação {@link Past @Path}. Esta classe servirá como início para o live coding.
 */
public class PastValidatorForLocalDate implements ConstraintValidator<Past, LocalDate> {

    @Override
    public void initialize(Past constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext cvc) {
        //null values are valid
        if (date == null) {
            return true;
        }
        return date.isBefore(LocalDate.now());
    }
}
