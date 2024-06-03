package de.telran.payment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.payment.enums.StatusPayment;
import de.telran.payment.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderDto {
    private long id;
    private long orderId;
    private String paymentId;
    private Type type;
    private StatusPayment status;
    private BigDecimal amount;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("recipient")
    private RecipientDto recipient;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sender")
    private SenderDto sender;
}

