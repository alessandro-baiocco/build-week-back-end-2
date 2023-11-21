package application.U5D16.payloads.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record EmailDTO(
        @NotEmpty(message = "L'email del destinatario è obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")

        String recipient,
        @NotEmpty(message = "L'oggetto dell'email è obbligatorio")
        String object,
        @NotEmpty(message = "il contenuto dell'email è obbligatorio")
        String contents
        ) {
}
