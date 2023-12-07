package utlc.ru.project1.dto.administrator;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import utlc.ru.project1.database.entity.Role;

public record AdministratorUpdateDto(
        @NotNull(message = "Username is required")
        @Pattern(regexp = ".*\\S.*", message = "Username must contain non-whitespace characters")
        @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
        String username,

        //TODO set pattern for a more sophisticated pattern for passwords
        @NotNull(message = "Password is required")
        @Pattern(regexp = ".*\\S.*", message = "Password must contain non-whitespace characters")
        @Size(min = 6, max = 128, message = "Password must be between 6 and 128 characters")
        String password,

        @Email
        String email,

        @NotNull(message = "First name is required")
        @Pattern(regexp = ".*\\S.*", message = "Firstname must contain non-whitespace characters")
        @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
        String firstname,

        @NotNull(message = "Last name is required")
        @Pattern(regexp = ".*\\S.*", message = "Lastname must contain non-whitespace characters")
        @Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters")
        String lastname,

        @Column(name = "role", nullable = false, length = 50)
        @Enumerated(value = EnumType.STRING)
        Role role
) {
}
