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
public class Recipient {  // получатель денег
    @Id
    @Column(name = "SenderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long senderId;

    @Column(name = "Name") // имя
    private String name;

    @Column(name = "Iban") // счет
    private String iban;

    @Column(name = "Card") // номер карты
    private String card;

    @Column(name = "paypalId") // ИД Paypal
    private String paypalId;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

    //связь один ко многим с PurchaseOrders по полю userId
    @OneToMany(mappedBy = "recipients", cascade = CascadeType.ALL)
    private Set<PurchaseOrder> purchaseOrder = new HashSet<>();
//    private Note note;
//    private Senders senders;
}
