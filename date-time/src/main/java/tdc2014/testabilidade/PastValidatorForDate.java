package tdc2014.testabilidade;

import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Past;

/**
 * Check that the <code>java.util.Date</code> passed to be validated is in the past.
 *
 * @author Alaa Nassef
 */
public class PastValidatorForDate implements ConstraintValidator<Past, Date> {

    @Override
    public void initialize(Past constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext cvc) {
        //null values are valid
        if (date == null) {
            return true;
        }
        return date.before(new Date());
    }
}
