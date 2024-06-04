package de.telran.payment.dto;

import de.telran.payment.entity.PurchaseOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipientDto {
    private long id;
    private String name;

    private String iban;

    private String card;

    private String paypalId;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PurchaseOrder> purchaseOrder = new HashSet<>();


}
