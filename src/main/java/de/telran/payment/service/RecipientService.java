package de.telran.payment.service;

import de.telran.payment.dto.RecipientDto;
import de.telran.payment.entity.Recipient;
import de.telran.payment.repository.RecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipientService {
    private final RecipientRepository recipientRepository;

    public List<RecipientDto> getRecipient(){
        List<Recipient> recipientList = recipientRepository.findAll();

        List<RecipientDto> recipientDtoList =
                recipientList.stream()
                        .map(r -> RecipientDto.builder()
                                .id(r.getId())
                                .build())
                                .collect(Collectors.toList());
        return recipientDtoList;
    }
}
