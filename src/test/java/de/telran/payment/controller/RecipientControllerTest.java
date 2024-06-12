package de.telran.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.payment.dto.RecipientDto;
import de.telran.payment.service.RecipientService;
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

@WebMvcTest(RecipientController.class)

class RecipientControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecipientService recipientServiceMock;

    private RecipientDto recipientExpected1;
    private RecipientDto recipientExpected2;

    @BeforeEach
    void setUp() {
        recipientExpected1 = RecipientDto.builder()
                .id(1L)
                .card("1234567890")
                .iban("DE1234567890")
                .paypalId("PI1234567890")
                .name("TestName1")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        recipientExpected2 = RecipientDto.builder()
                .id(2L)
                .card("0034567890")
                .iban("DE0034567890")
                .paypalId("PI0034567890")
                .name("TestName2")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    @Test
    void getRecipientTest() throws Exception {
         when(recipientServiceMock.getRecipient()).thenReturn(List.of(recipientExpected1, recipientExpected2));
        this.mockMvc.perform(get("/recipient")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..name").exists());
    }

    @Test
    void getRecipientByIdTest() throws Exception {
        when(recipientServiceMock.getRecipientById(2L)).thenReturn(recipientExpected2);
        this.mockMvc.perform(get("/recipient/{id}", 2)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    void insertRecipientTest() throws Exception {
        RecipientDto recipientInsert = RecipientDto.builder()
                .card("1234567890")
                .iban("DE1234567890")
                .paypalId("PI1234567890")
                .name("TestName1")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        // Имитируется любой объект класса RecipientDto в качестве параметра
        when(recipientServiceMock.insertRecipient(any(RecipientDto.class))).thenReturn(recipientExpected1);

        String requestBody = objectMapper.writeValueAsString(recipientInsert);

        this.mockMvc.perform(post("/recipient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(recipientInsert.getName()))
        ;



        //  Можно передавать конкретный json в качестве параметра
        //        this.mockMvc.perform(put("/recipient")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                    "userID":1
//                                }
//                                """))
//                .andDo(print())
//                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.userID").exists());
//                .andExpect(jsonPath("$.userID").value(1));


    }

    @Test
    void deleteRecipientByIdTest() {
//(recipientServiceMock.deleteRecipientById(1L)).thenReturn(null);
        //   this.mockMvc.perform(recipientServiceMock.deleteRecipientById(1L))
    }

    @Test
    void updateRecipientTest() throws Exception {
        RecipientDto updatedRecipient = RecipientDto.builder()
                .id(1L)
                .name("Unknown")
                .paypalId("987654321")
                .card("2547891")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        when(recipientServiceMock.updateRecipient(any(RecipientDto.class))).thenReturn(updatedRecipient);
        String requestBody = objectMapper.writeValueAsString(updatedRecipient);
        this.mockMvc.perform(put("/recipient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1));
    }
}