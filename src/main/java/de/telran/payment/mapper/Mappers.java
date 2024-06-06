package de.telran.payment.mapper;

import de.telran.payment.dto.PurchaseOrderDto;
import de.telran.payment.dto.RecipientDto;
import de.telran.payment.entity.PurchaseOrder;
import de.telran.payment.entity.Recipient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class Mappers {
    private final ModelMapper modelMapper;

    public RecipientDto convertToRecipientDto(Recipient recipient) {
        // перенастраиваю автомат, чтобы он пропустил поле iban и не отдал его клиенту
        modelMapper.typeMap(Recipient.class, RecipientDto.class)
                .addMappings(mapper -> mapper.skip(RecipientDto::setIban));

        RecipientDto recipientDto = modelMapper.map(recipient, RecipientDto.class); //автомат
        return recipientDto;
    }

    public Recipient convertToRecipient(RecipientDto recipientDto) {
        Recipient recipient = modelMapper.map(recipientDto, Recipient.class); //автомат
        recipient.setUpdatedAt(new Timestamp(System.currentTimeMillis())); // можем заменить собственнім значением
        return recipient;
    }

    public PurchaseOrderDto convertToPurchaseOrderDto(PurchaseOrder purchaseOrder) {
        PurchaseOrderDto purchaseOrderDto = modelMapper.map(purchaseOrder, PurchaseOrderDto.class); //автомат
        //подключаем руками нужный нам конвертор для подчиненного объекта, вместо автоматического
        purchaseOrderDto.setRecipient(convertToRecipientDto(purchaseOrder.getRecipient()));
        return purchaseOrderDto;
    }


}
