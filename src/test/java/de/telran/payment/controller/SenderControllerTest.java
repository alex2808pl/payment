package de.telran.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.payment.dto.SenderDto;
import de.telran.payment.service.SenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SenderController.class)
class SenderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SenderService senderServiceMock;

    private SenderDto senderExpected1;
    private SenderDto senderExpected2;


    @BeforeEach
    void setUp() {
        senderExpected1 = SenderDto.builder()
                .id(1L)
                .name("Bob")
                .card("1324354657")
                .iban("FR627384585")
                .paypalId("0x39217836")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        senderExpected2 = SenderDto.builder()
                .id(2L)
                .name("Tom")
                .card("76543221")
                .iban("IT62734321")
                .paypalId("0xt47585")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    @Test
    void getSenderTest() throws Exception {
        when(senderServiceMock.getSender()).thenReturn(List.of(senderExpected1, senderExpected2));
        this.mockMvc.perform(get("/sender")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..name").exists());
    }

    @Test
    void getSenderByIdTest() throws Exception {
        when(senderServiceMock.getSenderById(2L)).thenReturn(senderExpected2);
        this.mockMvc.perform(get("/sender/{id}", 2)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void insertSenderTest() throws Exception {
        SenderDto senderInsert = SenderDto.builder()
                .name("Tom")
                .card("1324354657")
                .iban("FR627384585")
                .paypalId("0x39217836")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        when(senderServiceMock.insertSender(any(SenderDto.class))).thenReturn(senderExpected2);

        String requestBody = objectMapper.writeValueAsString(senderInsert);

        this.mockMvc.perform(post("/sender")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value(senderInsert.getName()));

    }

    @Test
    void deleteSenderByIdTest() throws Exception {
        when(senderServiceMock.getSenderById(2L)).thenReturn(senderExpected2);
        this.mockMvc.perform(delete("/sender/{id}", 2)).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").doesNotExist());
    }

    @Test
    void updateSenderTest() throws Exception {
        SenderDto senderUpdate = SenderDto.builder()
                .id(1L)
                .name("Kat")
                .card("1324354657")
                .iban("FR627384585")
                .paypalId("0x39217836")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        when(senderServiceMock.updateSender(any(SenderDto.class))).thenReturn(senderUpdate);

        String requestBody = objectMapper.writeValueAsString(senderUpdate);

        this.mockMvc.perform(put("/sender")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(senderUpdate.getName()));

    }
}

