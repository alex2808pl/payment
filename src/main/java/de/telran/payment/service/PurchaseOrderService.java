package de.telran.payment.service;

import de.telran.payment.config.MapperUtil;
import de.telran.payment.dto.PurchaseOrderDto;
import de.telran.payment.dto.SenderDto;
import de.telran.payment.entity.PurchaseOrder;
import de.telran.payment.entity.Sender;
import de.telran.payment.mapper.Mappers;
import de.telran.payment.repository.PurchaseOrderRepository;
import de.telran.payment.repository.RecipientRepository;
import de.telran.payment.repository.SenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SenderRepository senderRepository;
    private final RecipientRepository recipientRepository;

    private final Mappers mappers;

    public List<PurchaseOrderDto> getPurchaseOrder() {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        List<PurchaseOrderDto> purchaseOrderDtoList = MapperUtil.convertList(purchaseOrderList, mappers::convertToPurchaseOrderDto);
        return purchaseOrderDtoList;
    }

    public PurchaseOrderDto getPurchaseOrderById(Long id) {
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(id);
        PurchaseOrderDto purchaseOrderDto = null;
        if (purchaseOrderOptional.isPresent()) {
            purchaseOrderDto = purchaseOrderOptional.map(mappers::convertToPurchaseOrderDto).orElse(null);
        }
        return purchaseOrderDto;
    }

    public void deletePurchaseOrderById(Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        if (purchaseOrder.isPresent()) {
            purchaseOrderRepository.delete(purchaseOrder.get());
        }
    }

    public PurchaseOrderDto insertPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder newPurchaseOrder = mappers.convertToPurchaseOrder(purchaseOrderDto);
        newPurchaseOrder.setId(0);
        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(newPurchaseOrder);
        return mappers.convertToPurchaseOrderDto(savedPurchaseOrder);
    }

    public PurchaseOrderDto updatePurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        if (purchaseOrderDto.getId() <= 0) {
            return null;
        }
        // Ищем такой объект в БД
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(purchaseOrderDto.getId());
        if (!purchaseOrderOptional.isPresent()) {
            // Объект в БД не найден с таким purchaseOrderId, нужно вывести пользователю ошибку
            return null;
        }

        // Получаю связанного Users
        SenderDto senderDto = purchaseOrderDto.getSender();
        Sender sender = null;
        if (senderDto != null && senderDto.getId() != null) {
            Optional<Sender> senderOptional = senderRepository.findById(senderDto.getId());
            if (senderOptional.isPresent()) {
                sender = senderOptional.get();
            }
        }
        PurchaseOrder purchaseOrder = purchaseOrderOptional.get();
        purchaseOrder.setOrderId(purchaseOrderDto.getOrderId());
        purchaseOrder.setSender(sender);

        //Сохраняю в БД
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        PurchaseOrder savedCategory = purchaseOrderRepository.save(purchaseOrder);

        return mappers.convertToPurchaseOrderDto(savedCategory);
    }
}