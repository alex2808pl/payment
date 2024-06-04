package de.telran.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.payment.entity.PurchaseOrder;
import jakarta.persistence.CascadeType;
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
public class SenderDto {
    private Long id;

    private String name;

    private String iban;

    private String card;

    private String paypalId;

    private Timestamp createdAt;

    private Timestamp updatedAt;


}
