package de.telran.payment.service;

import de.telran.payment.config.MapperUtil;
import de.telran.payment.dto.RecipientDto;
import de.telran.payment.entity.Recipient;
import de.telran.payment.mapper.Mappers;
import de.telran.payment.repository.RecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipientService {
    private final RecipientRepository recipientRepository;
    private final Mappers mappers;

    public List<RecipientDto> getRecipient(){
        List<Recipient> recipientList = recipientRepository.findAll();

        List<RecipientDto> recipientDtoList = MapperUtil.convertList(recipientList, mappers::convertToRecipientDto);

        return recipientDtoList;
    }

    public RecipientDto getRecipientById(Long id) {
        Optional<Recipient> recipient = recipientRepository.findById(id);
        RecipientDto recipientDto = null;
        if (recipient.isPresent()) {
            recipientDto = mappers.convertToRecipientDto(recipient.get());
        }
        return recipientDto;
    }


    public RecipientDto insertRecipient(RecipientDto recipientDto) {
        // Преобразовую Dto в Entity
         Recipient recipient = mappers.convertToRecipient(recipientDto);
        recipient.setId(null); // ведь мы добавляем

        Recipient newRecipient = recipientRepository.save(recipient);

        // Преобразовую Entity в Dto
        return  mappers.convertToRecipientDto(newRecipient);
    }


    public void deleteRecipientById(Long id) {
        Optional<Recipient> recipient = recipientRepository.findById(id);
        if (recipient.isPresent()) {
            recipientRepository.delete(recipient.get());
        }

    }

    public RecipientDto updateRecipient(RecipientDto recipientDto) {
        if (recipientDto.getId() <= 0) {
            return null;
        }

        Optional<Recipient> recipientOptional = recipientRepository.findById(recipientDto.getId());
        if (!recipientOptional.isPresent()) {
            return null;
        }

        Recipient recipient = mappers.convertToRecipient(recipientDto);

        Recipient newRecipient = recipientRepository.save(recipient);
        return mappers.convertToRecipientDto(newRecipient);
    }
}
