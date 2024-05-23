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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PurchaseOrders") //Заказы
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseOrders {
    @Id
    @Column(name = "PurchaseOrderId") //Идентификатор заказа на поставку
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseOrderId;

    @Column(name = "OrderId") //Номер заказа
    private long orderId;

    @Column(name = "RecipientId") // получатель денежных средств
    private String recipientId;

    @Column(name = "UserId") //ID пользователя
    private String userId;

    @Column(name = "PaymentId") // ИД платежа в платежной системе
    private String paymentId;

    @Column(name = "Type") //Тип
    private Type type;

    @Column(name = "Status") //статус
    private StatusPayment status;

    @Column(name = "Amount") //Количество
    private BigDecimal amount;

    @Column(name = "CreatedAt") //Создан в
    private Timestamp createdAt;

    @Column(name = "UpdatedAt") //Обновлено в
    private Timestamp updatedAt;

//    @OneToMany(mappedBy = "purchaseOrders", cascade = CascadeType.ALL)
//    private Set<Recipients> recipients = new HashSet<>();
}