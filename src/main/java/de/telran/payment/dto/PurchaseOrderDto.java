package de.telran.payment.dto;

import de.telran.payment.entity.Recipient;
import de.telran.payment.entity.Sender;
import de.telran.payment.enums.StatusPayment;
import de.telran.payment.enums.Type;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PurchaseOrderDto {
    private Long id;

    private long orderId;

    private String paymentId;

    private Type type;

    private StatusPayment status;

    private BigDecimal amount;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipientId", nullable=false)
    private Recipient recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="senderId", nullable=false)
    private Sender sender;
}
