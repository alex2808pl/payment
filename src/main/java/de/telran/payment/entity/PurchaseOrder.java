package de.telran.payment.entity;

import de.telran.payment.enums.StatusPayment;
import de.telran.payment.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "purchase_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long orderId;

    //@Column(name = "RecipientId") // получатель денежных средств
    //private String recipientId;

    //@Column(name = "SenderId") //ID пользователя
    //private String senderId;

    //@Column(name = "PaymentId") // ИД платежа в платежной системе
    private String paymentId;

    private Type type;

    private StatusPayment status;

    private BigDecimal amount;

    //@Column(name = "created_at")
    private Timestamp createdAt;

    //@Column(name = "updatedAt")
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipientId", nullable=false)
    private Recipient recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="senderId", nullable=false)
    private Sender sender;
}