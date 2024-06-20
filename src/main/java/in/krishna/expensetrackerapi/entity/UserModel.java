package in.krishna.expensetrackerapi.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @NotBlank(message = "Name should not be empty")
    private String name;
    @NotNull(message = "Email should not be empty")
    @Email(message = "Enter Valid email")
    private String email;
    @NotNull(message = "Password should not be empty")
    @Size(min = 8, message = "Password should be atleast 5 char long")
    private String password;
//    private Long age = 0L;
    private BigDecimal budget = new BigDecimal("0");
}
