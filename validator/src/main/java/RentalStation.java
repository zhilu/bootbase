import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * @author sgh2759
 * @since
 **/
public class RentalStation {

    public RentalStation(@NotNull String name) {
        //...
    }

    public void rentCar(@NotNull @Future LocalDate startDate, @Min(1) int durationInDays) {
        //...
    }

    @NotNull
    @Size(min = 1)
    public List<@NotNull String> getCustomers() {
        //...
        return null;
    }
}