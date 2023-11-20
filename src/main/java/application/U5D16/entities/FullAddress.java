package application.U5D16.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FullAddress {
    @Id
    @GeneratedValue
    private UUID id;
    private String denominazioneInItaliano;
    private String provincia;
    private String sigla;
    private String regione;

    @Override
    public String toString() {
        return "FullAddress{" +
                "id=" + id +
                ", denominazioneInItaliano='" + denominazioneInItaliano + '\'' +
                ", provincia='" + provincia + '\'' +
                ", Sigla='" + sigla + '\'' +
                ", regione='" + regione + '\'' +
                '}';
    }

    public FullAddress(String denominazioneInItaliano, String provincia, String sigla, String regione) {
        this.denominazioneInItaliano = denominazioneInItaliano;
        this.provincia = provincia;
        this.sigla = sigla;
        this.regione = regione;
    }
}
