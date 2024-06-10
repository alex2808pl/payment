package de.telran.payment.service;

import de.telran.payment.dto.PurchaseOrderDto;
import de.telran.payment.entity.PurchaseOrder;
import de.telran.payment.mapper.Mappers;
import de.telran.payment.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static de.telran.payment.enums.StatusPayment.NEW;
import static de.telran.payment.enums.Type.CARD;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PurchaseOrderServiceTest {
    @Mock
    private PurchaseOrderRepository purchaseOrderRepositoryMock;

    @Mock
    private Mappers mappersMock;

    @InjectMocks
    private PurchaseOrderService purchaseOrderServiceTest;


    private PurchaseOrderDto purchaseOrderDtoExpected1;
    private PurchaseOrderDto purchaseOrderDtoExpected2;

    private PurchaseOrder purchaseOrderEntityExpected1;
    private PurchaseOrder purchaseOrderEntityExpected2;


    @BeforeEach
    void setUp() {

        purchaseOrderEntityExpected1 = new PurchaseOrder(
                1L, 1L, "341343", CARD, NEW, BigDecimal.valueOf(2321000),
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), null, null
        );

        purchaseOrderEntityExpected2 = new PurchaseOrder(
                2L, 2L, "341343", CARD, NEW, BigDecimal.valueOf(2321000),
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), null, null
        );

        purchaseOrderDtoExpected1 = PurchaseOrderDto.builder()
                .id(1L)
                .orderId(1234L)
                .paymentId("PI1234567890")
                .type(CARD)
                .status(NEW)
                .amount(BigDecimal.valueOf(2321000))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        purchaseOrderDtoExpected2 = PurchaseOrderDto.builder()
                .id(2L)
                .orderId(1233L)
                .paymentId("PI1234567891")
                .type(CARD)
                .status(NEW)
                .amount(BigDecimal.valueOf(2321001))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPurchaseOrderTest() {
        when(purchaseOrderRepositoryMock.findAll()).thenReturn(List.of(purchaseOrderEntityExpected1, purchaseOrderEntityExpected2));

        when(mappersMock.convertToPurchaseOrderDto(purchaseOrderEntityExpected1)).thenReturn(purchaseOrderDtoExpected1);
        when(mappersMock.convertToPurchaseOrderDto(purchaseOrderEntityExpected2)).thenReturn(purchaseOrderDtoExpected2);

        List<PurchaseOrderDto> actualPurchaseOrderDtoList = purchaseOrderServiceTest.getPurchaseOrder();

        // Самостоятельно определяте набор проверок
        assertTrue(actualPurchaseOrderDtoList.size()>0);
        verify(mappersMock, times(2)).convertToPurchaseOrderDto(any(PurchaseOrder.class));
        assertEquals(purchaseOrderEntityExpected1.getId(), actualPurchaseOrderDtoList.get(0).getId());

    }

    @Test
    void insertPurchaseOrderTest() {
        when(purchaseOrderRepositoryMock.save(any(PurchaseOrder.class))).thenReturn(purchaseOrderEntityExpected1);
        when(mappersMock.convertToPurchaseOrder(any(PurchaseOrderDto.class))).thenReturn(purchaseOrderEntityExpected1);
        when(mappersMock.convertToPurchaseOrderDto(any(PurchaseOrder.class))).thenReturn(purchaseOrderDtoExpected1);

        PurchaseOrderDto purchaseOrderDtoActual = purchaseOrderServiceTest.insertPurchaseOrder(purchaseOrderDtoExpected1);

        assertNotNull(purchaseOrderDtoActual.getId());
        assertEquals(purchaseOrderDtoExpected1.getOrderId(), purchaseOrderDtoActual.getOrderId());
    }
}