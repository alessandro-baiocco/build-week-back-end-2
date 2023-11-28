package application.U5D16.payloads.client;

import application.U5D16.entities.enums.FormaGiuridica;
import jakarta.validation.constraints.*;

public record PutClientDTO (@NotEmpty(message = "la riagione sociale è un campo obbligatorio!")
                            @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
                            String ragioneSociale,
                            @NotEmpty(message = "la partita iva è un campo obbligatorio!")
                            @Size(min = 11, max=11, message = "La partita iva deve essere lunga esattamente 11 cifre")
                            Long partitaIva,
                            @NotEmpty(message = "la mail è un campo obbligatorio!")
                            @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
                            String email,
                            @NotNull
                            @Positive
                            Double fatturatoAnnuale,
                            @NotEmpty(message = "la mail è un campo obbligatorio!")
                            @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "La pec inserita non è valida")
                            String pec,
                            @NotNull
                            @Positive
                            Long telefono,
                            @NotEmpty(message = "la mail contatto è un campo obbligatorio!")
                            @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email contatto inserita non è valida")
                            String emailContatto,
                            @NotEmpty(message = "il nome è un campo obbligatorio!")
                            @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
                            String nomeContatto,
                            @NotEmpty(message = "il cognome  è un campo obbligatorio!")
                            @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
                            String cognomeContatto,
                            @NotNull
                            Long telefonoContatto,
                            @NotEmpty(message = "la forma giuridica è un campo obbligatorio!")
                            @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
                            FormaGiuridica formaGiuridica){
}
