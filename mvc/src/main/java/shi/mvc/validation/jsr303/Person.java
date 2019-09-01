package shi.mvc.validation.jsr303;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
public class Person{
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    
    @NotNull
    private String phone;
    
    @Size(max=5)
    private String address;

    @AssertTrue()
    private boolean biger;
    
    
    /**
     * aspect and invoke 
     * @return
     */
    @AssertTrue(message="at least one name")
    public boolean oneName() {
		return null != lastName && null != firstName;
    }
    
    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}