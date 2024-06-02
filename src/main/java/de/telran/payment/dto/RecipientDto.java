package de.telran.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipientDto {
    private Long id;
    private String name;
    private String iban;
    private String card;
    private String paypalId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
