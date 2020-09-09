import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author sgh2759
 * @since
 **/
public class UserTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validatorTest() {
        User user = new User(null, "", "!@#$", null, 11);

        // 验证所有bean的所有约束
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        // 验证单个属性
        Set<ConstraintViolation<User>> constraintViolations2 = validator.validateProperty(user, "name");
        // 检查给定类的单个属性是否可以成功验证
        Set<ConstraintViolation<User>> constraintViolations3 = validator.validateValue(User.class, "password", "sa!");

        constraintViolations.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
        System.out.println("---------");
        constraintViolations2.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
        System.out.println("---------");
        constraintViolations3.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
    }
}