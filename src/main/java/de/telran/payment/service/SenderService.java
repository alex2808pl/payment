package de.telran.payment.service;

import de.telran.payment.dto.PurchaseOrderDto;
import de.telran.payment.dto.SenderDto;
import de.telran.payment.entity.PurchaseOrder;
import de.telran.payment.entity.Sender;
import de.telran.payment.repository.PurchaseOrderRepository;
import de.telran.payment.repository.SenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SenderService {
    private final SenderRepository senderRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public List<SenderDto> getSender() {
        List<Sender> senderList = senderRepository.findAll();

        List<SenderDto> senderDtoList =
                senderList.stream()
                        .map(s -> SenderDto.builder()
                                .id(s.getId())
                                .build())
                        .collect(Collectors.toList());
        return senderDtoList;
    }

    public SenderDto getSenderById(Long id) {
        Optional<Sender> sender = senderRepository.findById(id);
        SenderDto senderDto = null;
        if (sender.isPresent()) {
            senderDto = new SenderDto(sender.get().getId(),
                    sender.get().getName(), sender.get().getIban(),
                    sender.get().getCard(), sender.get().getPaypalId(),
                    sender.get().getCreatedAt(), sender.get().getUpdatedAt());
        }
        return senderDto;
    }


    public void deleteSenderById(Long id) {
        Optional<Sender> sender = senderRepository.findById(id);
        if (sender.isPresent()) {
            senderRepository.delete(sender.get());
        }
    }

    public SenderDto insertSender(SenderDto senderDto) {

        // преобразуем Dto to Entity
        Sender sender = new Sender(0L, "Marta", "GHJ34556",
                "1324263637474", "87656543216676",
                Timestamp.valueOf("2021-01-01 00:00:02.94001"), Timestamp.valueOf("2021-01-01 00:00:02.94001"), null);
//   сохраняем в БД
        sender = senderRepository.save(sender);
// трансформируем в Dto
        SenderDto responseSenderDto = new SenderDto(sender.getId(),
                sender.getName(), sender.getIban(),
                sender.getCard(), sender.getPaypalId(),
                sender.getCreatedAt(), sender.getUpdatedAt());
        return responseSenderDto;
    }

    public SenderDto updateSender(SenderDto senderDto) {
        if (senderDto.getId() <= 0) {
            // при редактировании такого быть не должно, нужно вывести пользователю ошибку
            return null;
        }
        // Ищем такой объект в БД
        Optional<Sender> senderOptional = senderRepository.findById(senderDto.getId());
        if (!senderOptional.isPresent()) {
            // объект в БД не найден с таким getId, нужно вывести ошибку пользователю
            return null;
        }

        Sender sender = senderOptional.get();
        sender.setId(senderDto.getId());
        //  sender.setPurchaseOrder(purchaseOrder);


//   сохраняем в БД
        sender = senderRepository.save(sender);
// трансформируем в Dto
        SenderDto responseSenderDto = new SenderDto(sender.getId(),
                sender.getName(), sender.getIban(),
                sender.getCard(), sender.getPaypalId(),
                sender.getCreatedAt(), sender.getUpdatedAt());
        return responseSenderDto;

    }
}
