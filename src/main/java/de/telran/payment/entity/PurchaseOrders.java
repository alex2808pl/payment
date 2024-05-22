package de.telran.payment.entity;

import de.telran.payment.entity.enums.StatusPayment;
import de.telran.payment.entity.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "PurchaseOrders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseOrders {
    @Id
    @Column(name = "PurchaseOrderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseOrderId;

    @Column(name = "OrderId")
    private long orderId;

    @Column(name = "RecipientID") // получатель денежных средств
    private String recipientId;

    @Column(name = "UserID")
    private String userId;

    @Column(name = "PaymentID") // ИД платежа в платежной системе
    private String paymentId;

    @Column(name = "Type")
    private Type type;

    @Column(name = "Status")
    private StatusPayment status;

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

}