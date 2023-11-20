package application.U5D16.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    private UUID id;
    private String via;
    private String località;
    private int cap;
    private String comune;

    @CreationTimestamp
    private Date createdAt;


    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
