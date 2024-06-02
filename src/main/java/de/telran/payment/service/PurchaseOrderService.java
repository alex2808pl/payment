package de.telran.payment.service;

import de.telran.payment.dto.PurchaseOrderDto;
import de.telran.payment.dto.SenderDto;
import de.telran.payment.entity.PurchaseOrder;
import de.telran.payment.entity.Recipient;
import de.telran.payment.entity.Sender;
import de.telran.payment.repository.PurchaseOrderRepository;
import de.telran.payment.repository.RecipientRepository;
import de.telran.payment.repository.SenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SenderRepository senderRepository;
    private final RecipientRepository recipientRepository;

    public List<PurchaseOrderDto> getPurchaseOrder() {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
// преобразую пока вручную
        List<PurchaseOrderDto> purchaseOrderDtoList =
                purchaseOrderList.stream()
                        .map(f -> PurchaseOrderDto.builder()
                                .id(f.getId())
                                .orderId(f.getOrderId())
                                .sender(new SenderDto())
                                .build())
                        .collect(Collectors.toList());
        return purchaseOrderDtoList;
    }

    public PurchaseOrderDto getPurchaseOrderById(Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        PurchaseOrderDto purchaseOrderDto = null;
        if (purchaseOrder.isPresent()) {
            purchaseOrderDto = new PurchaseOrderDto(purchaseOrder.get().getId(),
                    purchaseOrder.get().getOrderId(), purchaseOrder.get().getPaymentId(), purchaseOrder.get().getType(), purchaseOrder.get().getStatus(), purchaseOrder.get().getAmount(), purchaseOrder.get().getCreatedAt(), purchaseOrder.get().getUpdatedAt(), null, null);
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


        // Получаю связанного Sender
        SenderDto senderDto = purchaseOrderDto.getSender();
        Sender sender = null;
        Recipient recipient = null;
        if (senderDto != null && senderDto.getId() != null) {
        }
        Optional<Sender> senderOptional = senderRepository.findById(senderDto.getId());
        if (senderOptional.isPresent()) {
            sender = senderOptional.get();
        }

        // Преобразовую Dto B Entity
        PurchaseOrder purchaseOrder = new PurchaseOrder(0l, purchaseOrderDto.getOrderId(), purchaseOrderDto.getPaymentId(), purchaseOrderDto.getType(),  purchaseOrderDto.getStatus(), purchaseOrderDto.getAmount(),purchaseOrderDto.getCreatedAt(), purchaseOrderDto.getUpdatedAt(), recipient, sender);
        //Сохраняю в БД
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        // трансформируем в Dto
        PurchaseOrderDto responsePurchaseOrderDto = new PurchaseOrderDto(purchaseOrder.getId(),
                purchaseOrder.getOrderId(), purchaseOrder.getPaymentId(), purchaseOrder.getType(), purchaseOrder.getStatus(), purchaseOrder.getAmount(), purchaseOrder.getCreatedAt(), purchaseOrder.getUpdatedAt(), null, null);
        return responsePurchaseOrderDto;

    }

    public PurchaseOrderDto updatePurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        if (purchaseOrderDto.getId() <= 0) {
            // При редактировании такого быть не должно, нужно вывести пользователю ошибку
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

            // трансформируем в Dto
            PurchaseOrderDto responsePurchaseOrderDto = new PurchaseOrderDto(purchaseOrder.getId(),
                    purchaseOrder.getOrderId(), purchaseOrder.getPaymentId(), purchaseOrder.getType(), purchaseOrder.getStatus(), purchaseOrder.getAmount(), purchaseOrder.getCreatedAt(), purchaseOrder.getUpdatedAt(), null, null);

            return responsePurchaseOrderDto;
    }
}