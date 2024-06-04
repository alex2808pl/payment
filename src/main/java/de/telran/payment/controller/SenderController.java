package de.telran.payment.controller;

import de.telran.payment.dto.SenderDto;
import de.telran.payment.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public SenderDto updateSender(@RequestBody SenderDto senderDto) {
        return senderService.updateSender(senderDto);
    }

}
