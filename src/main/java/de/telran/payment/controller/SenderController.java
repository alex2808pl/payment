package de.telran.payment.controller;

import de.telran.payment.dto.SenderDto;
import de.telran.payment.exception.ErrorParamException;
import de.telran.payment.exception.NotFoundInDbException;
import de.telran.payment.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;



import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sender")
public class SenderController {
    private final SenderService senderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SenderDto> getSender() {
        return senderService.getSender();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SenderDto getSenderById(@PathVariable Long id) {
        return senderService.getSenderById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSenderById(@PathVariable Long id) {
        senderService.deleteSenderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SenderDto insertSender(@RequestBody SenderDto senderDto) {
        return senderService.insertSender(senderDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SenderDto updateSender(@RequestBody SenderDto senderDto) throws FileNotFoundException {
        return senderService.updateSender(senderDto);
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
}
