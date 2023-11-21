package application.U5D16.payloads.client;

import application.U5D16.entities.enums.FormaGiuridica;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;

public record ClientDTO(
        @NotEmpty(message = "La ragione sociale è obbligatoria")
        @Size(min = 2, max = 50, message = "La ragione sociale deve essere compresa tra 2 e 50 caratteri")
        String ragioneSociale,
        @NotEmpty(message = "La partita iva è obbligatoria")
        @Size(min = 11, max = 11, message = "La partita iva deve essere di 11 cifre")
        Long partitaIva,
        @NotEmpty(message = "L'email' è obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email non è valida")
        String email,
        @NotNull(message = "La data d'inserimento è obbligatoria")
        LocalDate dataInserimento,
        @NotNull(message = "La data dell'ultimo contatto è obligatoria")
        LocalDate dataUltimoContatto,
        @NotNull(message = "Il fatturato annuale è obbligatorio")
        Double fatturatoAnnuale,
        @NotEmpty(message = "La pec è obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email non è valida")
        String pec,
        @NotNull(message = "Il numero di telefono è obbligatorio")
        @Size(min = 6, max = 9, message = "Il numero di telefono può contenere un minimo di 6 e un massimo di 9 cifre")
        Long telefono,
        @NotEmpty(message = "L'email del contatto è obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email non è valida")
        String emailContatto,
        @NotEmpty(message = "Il nome del conttatto è obbligatorio")
        @Size(min = 3, message = "Il nome deve contenere almeno 3 caratteri")
        String nomeContatto,
        @NotEmpty(message = "Il cognome del conttatto è obbligatorio")
        @Size(min = 3, message = "Il cognome deve contenere almeno 3 caratteri")
        String cognomeContatto,
        @NotNull(message = "Il numero di telefono del contatto è obbligatorio")
        @Size(min = 6, max = 9, message = "Il numero di telefono del contatto può contenere un minimo di 6 e un massimo di 9 cifre")
        Long telefonoContatto,
        @NotEmpty(message = "La forma giuridica è obbligatoria" )
        FormaGiuridica formaGiuridica


        ) {
}
