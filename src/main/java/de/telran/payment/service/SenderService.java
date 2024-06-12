package de.telran.payment.service;

import de.telran.payment.config.MapperUtil;
import de.telran.payment.dto.SenderDto;
import de.telran.payment.entity.Sender;
import de.telran.payment.mapper.Mappers;
import de.telran.payment.repository.SenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SenderService {
    private final SenderRepository senderRepository;
    private final Mappers mappers;


    public List<SenderDto> getSender() {
        List<Sender> senderList = senderRepository.findAll();

        List<SenderDto> senderDtoList = MapperUtil.convertList(senderList, mappers::convertToSenderDto);

        return senderDtoList;
    }

    public SenderDto getSenderById(Long id) {
        Optional<Sender> sender = senderRepository.findById(id);
        SenderDto senderDto = null;
        if (sender.isPresent()) {
            senderDto = mappers.convertToSenderDto(sender.get());
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
        Sender sender = mappers.convertToSender(senderDto);
        sender.setId(null); // ведь мы добавляем

//   сохраняем в БД
        Sender newSender = senderRepository.save(sender);

        // трансформируем Entity в Dto
        return mappers.convertToSenderDto(newSender);
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
        Sender newSender = senderRepository.save(sender);
// трансформируем в Dto
        SenderDto responseSenderDto = mappers.convertToSenderDto(newSender);
        return responseSenderDto;
    }
}
