package de.telran.payment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipient")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String iban;

    private String card;

    private String paypalId;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PurchaseOrder> purchaseOrder = new HashSet<>();

}
