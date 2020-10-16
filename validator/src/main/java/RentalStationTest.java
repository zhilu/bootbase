import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author sgh2759
 * @since
 **/
public class RentalStationTest {

    private static ExecutableValidator executableValidator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        executableValidator = factory.getValidator().forExecutables();
    }

    @Test
    public void validatorTest() throws NoSuchMethodException {
        RentalStation rentalStation = new RentalStation("z");

        Method method = RentalStation.class.getMethod("rentCar", LocalDate.class, int.class);
        Object[] parameterValues = {LocalDate.now().minusDays(1), 0};
        Set<ConstraintViolation<RentalStation>> violations = executableValidator.validateParameters(
                rentalStation, method, parameterValues);

        violations.forEach(violation -> System.out.println(violation.getMessage()));

    }
}