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
@Table(name = "Sender")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sender {
    @Id
    @Column(name = "SenderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String iban;

    private String card;

    private String paypalId;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private Set<PurchaseOrder> purchaseOrder = new HashSet<>();
}
