package de.telran.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.payment.dto.PurchaseOrderDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(PurchaseOrderController.class)

public class PurchaseOrderControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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
                .orderId(323132L)
                .paymentId("341343")
                .type(CARD)
                .status(NEW)
                .amount(BigDecimal.valueOf(2321000))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        purchaseOrderExpected2 = PurchaseOrderDto.builder()
                .id(2L)
                .orderId(343431L)
                .paymentId("3434324")
                .type(CARD)
                .status(NEW)
                .amount(BigDecimal.valueOf(344233))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    @Test
    void getPurchaseOrderTest() throws Exception {
        when(purchaseOrderServiceMock.getPurchaseOrder()).thenReturn(List.of(purchaseOrderExpected1, purchaseOrderExpected2));
        this.mockMvc.perform(get("/purchaseOrder")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..type").exists());
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
                .andExpect(jsonPath("$.type").value(purchaseOrderInsert.getOrderId()));
    }
}