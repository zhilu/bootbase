import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author sgh2759
 * @since
 **/
@Data
@AllArgsConstructor
public class User {

    private String id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z][0-9]")
    private String password;

    @NotNull
    private Integer age;

    @Max(10)
    @Min(1)
    private Integer level;
}
