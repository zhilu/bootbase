import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author sgh2759
 * @since
 **/
public class MobileTest {

    public void setMobile(@Mobile String mobile){
        // to do
    }

    private static ExecutableValidator executableValidator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        executableValidator = factory.getValidator().forExecutables();
    }

    @Test
    public void manufacturerIsNull() throws NoSuchMethodException {
        MobileTest mobileTest = new MobileTest();

        Method method = MobileTest.class.getMethod("setMobile", String.class);
        Object[] parameterValues = {"1111111"};
        Set<ConstraintViolation<MobileTest>> violations = executableValidator.validateParameters(
                mobileTest, method, parameterValues);

        violations.forEach(violation -> System.out.println(violation.getMessage()));
    }
}