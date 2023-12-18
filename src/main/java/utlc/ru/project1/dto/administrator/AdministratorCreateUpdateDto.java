package utlc.ru.project1.dto.administrator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import utlc.ru.project1.database.entity.Role;

public record AdministratorCreateUpdateDto(

        @NotNull(message = "validation.administrator.username.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.administrator.username.pattern")
        @Size(min = 2, max = 50, message = "validation.administrator.username.size")
        String username,

        //TODO set pattern for a more sophisticated pattern for passwords
        @NotNull(message = "validation.administrator.password.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.administrator.password.pattern")
        @Size(min = 6, max = 128, message = "validation.administrator.password.size")
        String password,

        @Email(message = "validation.administrator.email.invalid")
        String email,

        @NotNull(message = "validation.administrator.firstname.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.administrator.firstname.pattern")
        @Size(min = 2, max = 50, message = "validation.administrator.firstname.size")
        String firstname,

        @NotNull(message = "validation.administrator.lastname.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.administrator.lastname.pattern")
        @Size(min = 2, max = 50, message = "validation.administrator.lastname.size")
        String lastname,

        @NotNull(message = "validation.administrator.role.required")
        Role role
) {
}