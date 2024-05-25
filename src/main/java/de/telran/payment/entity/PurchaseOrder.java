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
@Table(name = "purchase_order") //Заказы
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseOrder {
    @Id
    //@Column(name = "PurchaseOrderId") //Идентификатор заказа на поставку
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //private long purchaseOrderId;
    private long id;

    @Column(name = "OrderId") //Номер заказа
    //@Column(name = "order_id") //Номер заказа is default
    //order_id
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
//    private Note note;
//    private Senders senders;
//    private Recipients recipients;
}