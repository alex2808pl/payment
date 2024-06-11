package de.telran.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.telran.payment.dto.PurchaseOrderDto;

import de.telran.payment.dto.RecipientDto;
import de.telran.payment.dto.SenderDto;
import de.telran.payment.service.PurchaseOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static de.telran.payment.enums.StatusPayment.NEW;
import static de.telran.payment.enums.Type.CARD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(PurchaseOrderController.class)

public class PurchaseOrderControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PurchaseOrderService purchaseOrderServiceMock;

    private PurchaseOrderDto purchaseOrderExpected1;
    private PurchaseOrderDto purchaseOrderExpected2;

    @BeforeEach
    void setUp() {
        purchaseOrderExpected1 = PurchaseOrderDto.builder()
                .id(1L)
                .orderId(1234L)
                .paymentId("PI1234567890")
                .type(CARD)
                .status(NEW)
                .amount(BigDecimal.valueOf(2321000))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .sender(SenderDto.builder().id(1L).build())
                .recipient(RecipientDto.builder().id(1L).build())
                .build();

        purchaseOrderExpected2 = PurchaseOrderDto.builder()
                .id(2L)
                .orderId(1233L)
                .paymentId("PI1234567891")
                .type(CARD)
                .status(NEW)
                .amount(BigDecimal.valueOf(2321001))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .sender(SenderDto.builder().id(2L).build())
                .recipient(RecipientDto.builder().id(2L).build())
                .build();
    }

    @Test
    void getPurchaseOrderTest() throws Exception {
        when(purchaseOrderServiceMock.getPurchaseOrder()).thenReturn(List.of(purchaseOrderExpected1, purchaseOrderExpected2));
        mockMvc.perform(get("/purchaseOrder")).andDo(print())
                .andExpect(status().is(404))
                .andExpect(jsonPath("$..id").exists());
    }
    @Test
    void testDeletePurchaseOrderItemsById() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/purchaseorderitems/{id}", id)).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.orderId").doesNotExist())
                .andExpect(jsonPath("$.amount").doesNotExist())
                .andExpect(jsonPath("$.paymentId").doesNotExist());
    }
    @Test
    void insertPurchaseOrderTest() throws Exception {
        PurchaseOrderDto purchaseOrderInsert = PurchaseOrderDto.builder()
                .id(1L)
                .orderId(323132L)
                .paymentId("341343")
                .type(CARD)
                .status(NEW)
                .amount(BigDecimal.valueOf(2321000))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .sender(SenderDto.builder().id(1L).build())
                .recipient(RecipientDto.builder().id(1L).build())
                .build();
        // Имитируется любой объект класса PurchaseOrderDto в качестве параметра
        when(purchaseOrderServiceMock.insertPurchaseOrder(any(PurchaseOrderDto.class))).thenReturn(purchaseOrderExpected1);

        String requestBody = objectMapper.writeValueAsString(purchaseOrderInsert);

        this.mockMvc.perform(post("/purchaseOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.orderId").value(323132L))
                .andExpect(jsonPath("$.paymentId").value("341343"))
                .andExpect(jsonPath("$.type").value(CARD))
                .andExpect(jsonPath("$.status").value(NEW))
                .andExpect(jsonPath("$.amount").value(BigDecimal.valueOf(2321000)))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.updatedAt").exists());
    }
    @Test
    void updatePurchaseOrderTest() throws Exception {
        PurchaseOrderDto expectedPurchaseOrder = PurchaseOrderDto.builder()
                .id(1L)
                .orderId(323132L)
                .paymentId("341343")
                .type(CARD)
                .status(NEW)
                .amount(BigDecimal.valueOf(2321000))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .sender(SenderDto.builder().id(1L).build())
                .recipient(RecipientDto.builder().id(1L).build())
                .build();

        when(purchaseOrderServiceMock.updatePurchaseOrder(any(PurchaseOrderDto.class))).thenReturn(expectedPurchaseOrder);
        this.mockMvc.perform(put("/purchaseOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 1
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.orderId").value(323132L))
                .andExpect(jsonPath("$.paymentId").value("341343"))
                .andExpect(jsonPath("$.type").value(CARD))
                .andExpect(jsonPath("$.status").value(NEW))
                .andExpect(jsonPath("$.amount").value(BigDecimal.valueOf(2321000)))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.updatedAt").exists());
    }
}