package de.telran.payment.mapper;

import de.telran.payment.dto.PurchaseOrderDto;
import de.telran.payment.dto.RecipientDto;
import de.telran.payment.dto.SenderDto;
import de.telran.payment.entity.PurchaseOrder;
import de.telran.payment.entity.Recipient;
import de.telran.payment.entity.Sender;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class Mappers {
    @Autowired
    private ModelMapper modelMapper;

    public RecipientDto convertToRecipientDto(Recipient recipient) {
        // перенастраиваю автомат, чтобы он пропустил поле iban и не отдал его клиенту
        modelMapper.typeMap(Recipient.class, RecipientDto.class)
                .addMappings(mapper -> mapper.skip(RecipientDto::setIban));

        RecipientDto recipientDto = modelMapper.map(recipient, RecipientDto.class); //автомат
        return recipientDto;
    }

    public PurchaseOrder convertToPurchaseOrder(PurchaseOrderDto categoriesDto) {
        PurchaseOrder categories = modelMapper.map(categoriesDto, PurchaseOrder.class);
        return categories;
    }

    public Recipient convertToRecipient(RecipientDto recipientDto) {
        Recipient recipient = modelMapper.map(recipientDto, Recipient.class); //автомат
        recipient.setUpdatedAt(new Timestamp(System.currentTimeMillis())); // можем заменить собственнім значением
        return recipient;
    }

    public PurchaseOrderDto convertToPurchaseOrderDto(PurchaseOrder purchaseOrder) {
        modelMapper.typeMap(PurchaseOrder.class, PurchaseOrderDto.class)
                .addMappings(mapper -> mapper.skip(PurchaseOrderDto::setSender));
        PurchaseOrderDto purchaseOrderDto = modelMapper.map(purchaseOrder, PurchaseOrderDto.class);
        purchaseOrderDto.setSender(null);
        return purchaseOrderDto;
//        PurchaseOrderDto purchaseOrderDto = modelMapper.map(purchaseOrder, PurchaseOrderDto.class); //автомат
//        //подключаем руками нужный нам конвертор для подчиненного объекта, вместо автоматического
//        purchaseOrderDto.setRecipient(convertToRecipientDto(purchaseOrder.getRecipient()));
//        return purchaseOrderDto;
    }

    public SenderDto convertToSenderDto(Sender sender) {
        modelMapper.typeMap(Sender.class, SenderDto.class)
                .addMappings(mapper -> mapper.skip(SenderDto::setIban));

        SenderDto senderDto = modelMapper.map(sender, SenderDto.class); //автомат
        return senderDto;
    }

    public Sender convertToSender(SenderDto senderDto) {
        Sender sender = modelMapper.map(senderDto, Sender.class); // автомат
        sender.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return sender;
    }
}
