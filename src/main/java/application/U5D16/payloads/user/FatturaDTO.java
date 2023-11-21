package application.U5D16.payloads.user;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.UUID;

public record FatturaDTO(@NotEmpty(message = "Please add the fattura") double importo,
                         @NotEmpty(message = "Please add the date")LocalDate data,
                         @NotEmpty(message = "Please add number") Long numero,
                         @NotEmpty(message = "Please add client id") UUID client) {
}
