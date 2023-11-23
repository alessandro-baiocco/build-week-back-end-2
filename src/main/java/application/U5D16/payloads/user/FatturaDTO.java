package application.U5D16.payloads.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record FatturaDTO(
        @NotNull(message = "l'importo della fattura è obbligatorio")
        double importo,
        @NotNull(message = "la data della fattura è obbligatoria")
        LocalDate data,
        @NotNull(message = "lo stato della fattura è obbligatoria . gli stati sono PAGATA , NONPAGATA , INVIATA , SCADUTA")
        String stato,
        @NotNull(message = "L'id del cliente è obbligatorio")
        UUID client) { }
