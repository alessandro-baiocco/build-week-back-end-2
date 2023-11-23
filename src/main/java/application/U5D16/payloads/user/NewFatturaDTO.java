package application.U5D16.payloads.user;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record NewFatturaDTO (
        @NotNull(message = "l'importo è un campo obbligatorio!")
        @PositiveOrZero(message = "l'importo deve essere positivo")
        double importo,
        @NotNull(message = "la data è un campo obbligatorio")
        LocalDate data,
        @NotNull(message = "il numero di fattura è un campo obbligatorio")
        long numero,
        @NotNull(message = "il cliente è un campo obbligatorio")
        UUID client) { }
