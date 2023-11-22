package application.U5D16.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fatture")
@Getter
@Setter
public class Fattura {
    @Id
    @GeneratedValue
    private UUID id;
    private double importo;
    private LocalDate data;
    private Long numero;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
