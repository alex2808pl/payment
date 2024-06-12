package de.telran.payment.service;

import de.telran.payment.dto.PurchaseOrderDto;
import de.telran.payment.dto.SenderDto;
import de.telran.payment.entity.PurchaseOrder;
import de.telran.payment.entity.Sender;
import de.telran.payment.exception.NotFoundInDbException;
import de.telran.payment.mapper.Mappers;
import de.telran.payment.repository.PurchaseOrderRepository;
import de.telran.payment.repository.SenderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j //для включения логгирования
@Service
@RequiredArgsConstructor
public class SenderService {
    private final SenderRepository senderRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final Mappers mappers;

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
        Sender sender = senderRepository.findById(id)
                .orElseThrow(() -> new NotFoundInDbException("Не найден пользователь с таким Id"));
        Optional<Sender> senderOptional = senderRepository.findById(id);
        SenderDto senderDto = null;
        if (senderOptional.isPresent()) {
            senderDto = new SenderDto(senderOptional.get().getId(),
                    senderOptional.get().getName(), senderOptional.get().getIban(),
                    senderOptional.get().getCard(), senderOptional.get().getPaypalId(),
                    senderOptional.get().getCreatedAt(), senderOptional.get().getUpdatedAt());
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

    public SenderDto updateSender(SenderDto senderDto) throws FileNotFoundException {
        if (senderDto.getId() <= 0) {
            // при редактировании такого быть не должно, нужно вывести пользователю ошибку
            //return null;
            //}
            // Ищем такой объект в БД
            Optional<Sender> senderOptional = senderRepository.findById(senderDto.getId());
            if (!senderOptional.isPresent()) {
                // объект в БД не найден с таким getId, нужно вывести ошибку пользователю
                log.error("---- Не найден пользователь с Id=" + senderDto.getId());
                throw new NotFoundInDbException("Не найден пользователь с Id=" + senderDto.getId());
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

        } else {
            throw new FileNotFoundException("Не корректный параметр senderDto");
        }
    }
}
