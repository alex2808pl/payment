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
@Table(name = "Recipients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Senders {  // отправитель денег
    @Id
    @Column(name = "RecipientId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipientId;

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

    //связь один ко многим с PurchaseOrders по полю
    @OneToMany(mappedBy = "senders", cascade = CascadeType.ALL)
    private Set<Recipients> recipients = new HashSet<>();


}
