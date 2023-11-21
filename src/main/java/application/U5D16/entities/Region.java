package application.U5D16.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Region {
    @Id
    @GeneratedValue
    private UUID id;
    private String denominazioneInItaliano;
    private String provincia;
    private String sigla;
    private String regione;

    @Override
    public String toString() {
        return denominazioneInItaliano + " " + provincia + " " + sigla + " " + regione + "\n";
    }

    public Region(String denominazioneInItaliano, String provincia, String sigla, String regione) {
        this.denominazioneInItaliano = denominazioneInItaliano;
        this.provincia = provincia;
        this.sigla = sigla;
        this.regione = regione;
    }
}
