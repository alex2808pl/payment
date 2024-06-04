package de.telran.payment.service;

import de.telran.payment.config.MapperUtil;
import de.telran.payment.dto.RecipientDto;
import de.telran.payment.entity.Recipient;
import de.telran.payment.repository.RecipientRepository;

import de.telran.payment.mapper.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public RecipientDto insertRecipient(RecipientDto recipientDto) {
        // Преобразовую Dto в Entity
         Recipient recipient = mappers.convertToRecipient(recipientDto);
        recipient.setId(null); // ведь мы добавляем

        Recipient newRecipient = recipientRepository.save(recipient);

        // Преобразовую Entity в Dto
        return  mappers.convertToRecipientDto(newRecipient);
    }
}
