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
//    @Column(name = "PurchaseOrderId") //Идентификатор заказа на поставку
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private long purchaseOrderId;
    private Long id;

//    @Column(name = "order_id") //Номер заказа
    //@Column(name = "order_id") //Номер заказа is default
    //order_id
    private long orderId;

//    @Column(name = "recipient_id") // получатель денежных средств
    private String recipientId;

//    @Column(name = "user_id") //ID пользователя
    private String userId;

//    @Column(name = "payment_id") // ИД платежа в платежной системе
    private String paymentId;

//    @Column(name = "type") //Тип
    private Type type;

//    @Column(name = "status") //статус
    private StatusPayment status;

//    @Column(name = "amount") //Количество
    private BigDecimal amount;

//    @Column(name = "created_at") //Создан в
    private Timestamp createdAt;

//    @Column(name = "updated_at") //Обновлено в
    private Timestamp updatedAt;

//    @OneToMany(mappedBy = "purchaseOrders", cascade = CascadeType.ALL)
//    private Set<Recipients> recipients = new HashSet<>();
    private Sender sender;
    private Recipient recipient;
}