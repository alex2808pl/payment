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
//    @Column(name = "sender_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "name") // имя
    private String name;

//    @Column(name = "iban") // счет
    private String iban;

//    @Column(name = "card") // номер карты
    private String card;

//    @Column(name = "paypal_id") // ИД Paypal
    private String paypalId;

//    @Column(name = "created_at")
    private Timestamp createdAt;

//    @Column(name = "updated_at")
    private Timestamp updatedAt;

    //связь один ко многим с PurchaseOrders по полю userId
    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PurchaseOrder> purchaseOrder = new HashSet<>();
    private Sender sender;
}
