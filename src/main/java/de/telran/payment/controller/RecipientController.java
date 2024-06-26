package de.telran.payment.controller;

import de.telran.payment.dto.RecipientDto;
import de.telran.payment.exception.ErrorParamException;
import de.telran.payment.exception.NotFoundInDbException;
import de.telran.payment.service.RecipientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipientDto getRecipientById(@PathVariable Long id) {
        return recipientService.getRecipientById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipientDto insertRecipient(@RequestBody RecipientDto recipientDto) {
        return recipientService.insertRecipient(recipientDto);
    }


    @ExceptionHandler(ErrorParamException.class)
    public ResponseEntity<ErrorMessage> handleException(ErrorParamException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(NotFoundInDbException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundInDbException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()+"!!!!!!!!!!"));
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipientById(@PathVariable Long id) {
        recipientService.deleteRecipientById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RecipientDto updateRecipient(@RequestBody RecipientDto recipientDto) {
        return recipientService.updateRecipient(recipientDto);
    }

}
