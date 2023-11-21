package application.U5D16.payloads.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String cognome,
        @NotEmpty(message = "lo username è un campo obbligatorio!")
        String username,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])\\S{8,15}$", message = "Your password must meet the following criteria:" +
                " at least 8 characters long, no more than 15 characters, include uppercase and lowercase letters, contain at least one number, " +
                "and at least one special character (e.g., @, #, $, %, etc.). Spaces are not allowed.")
        String password,

        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email) {}
