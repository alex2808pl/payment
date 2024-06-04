package de.telran.payment.controller;

import de.telran.payment.dto.RecipientDto;
import de.telran.payment.service.RecipientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/recipient")
public class RecipientController {
    private final RecipientService recipientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecipientDto> getRecipient(){
        return recipientService.getRecipient();
    }



}
