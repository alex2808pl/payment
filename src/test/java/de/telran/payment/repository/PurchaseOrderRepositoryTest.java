package de.telran.payment.repository;

import de.telran.payment.entity.PurchaseOrder;

import de.telran.payment.entity.Recipient;
import de.telran.payment.entity.Sender;
import de.telran.payment.enums.StatusPayment;
import de.telran.payment.enums.Type;
import jakarta.persistence.Column;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import static de.telran.payment.enums.StatusPayment.NEW;
import static de.telran.payment.enums.Type.CARD;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PurchaseOrderRepositoryTest {
    @Autowired
    private PurchaseOrderRepository purchaseOrderTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void deleteInBatch() {
    }

    @Test
    void testGet() {
        PurchaseOrder purchaseOrderExpected = new PurchaseOrder();

        Optional<PurchaseOrder> purchaseOrderActual = purchaseOrderTest.findById(1L);
        Assertions.assertTrue(purchaseOrderActual.isPresent());
        Assertions.assertEquals(purchaseOrderExpected.getId(), purchaseOrderActual.get().getId());
        Assertions.assertEquals(purchaseOrderExpected.getId(), purchaseOrderActual.get().getId());
    }

    @Test
    void testInsert() {
        PurchaseOrder purchaseOrderExpected = new PurchaseOrder();
        //purchaseOrderExpected.setRecipient();
        purchaseOrderExpected.setOrderId(323132L);
        purchaseOrderExpected.setRecipientId("452542");
        purchaseOrderExpected.setType(CARD);
        purchaseOrderExpected.setStatus(NEW);
        purchaseOrderExpected.setAmount(BigDecimal.valueOf(2321000));
        purchaseOrderExpected.setCreatedAt(Timestamp.valueOf("2024-05-25 14:33:02.940000"));
        purchaseOrderExpected.setUpdatedAt(Timestamp.valueOf("2024-05-24 14:33:02.941000"));
        Recipient recipientTest = new Recipient();

        recipientTest.setId(1L);
        purchaseOrderExpected.setRecipient(recipientTest);

        Sender senderTest = new Sender();
        senderTest.setId(1L);
        purchaseOrderExpected.setSender(senderTest);

        PurchaseOrder purchaseOrderActual = purchaseOrderTest.save(purchaseOrderExpected);

        Assertions.assertNotNull(purchaseOrderActual);
        Assertions.assertTrue(purchaseOrderExpected.getId() > 0);
        Assertions.assertEquals(purchaseOrderExpected.getId(), purchaseOrderActual.getId());
    }

    @Test
    void testEdit() {
        Optional<PurchaseOrder> purchaseOrderDb = purchaseOrderTest.findById(1L);
        Assertions.assertTrue(purchaseOrderDb.isPresent());
        System.out.println(purchaseOrderDb.get().getId());
        PurchaseOrder purchaseOrderExpected = purchaseOrderDb.get();
        purchaseOrderExpected.setId(1L);
        PurchaseOrder purchaseOrderActual = purchaseOrderTest.save(purchaseOrderExpected);
        Assertions.assertNotNull(purchaseOrderActual);
        Assertions.assertEquals(purchaseOrderExpected.getId(), purchaseOrderActual.getId());
        Assertions.assertNotEquals(purchaseOrderDb.get().getId(), purchaseOrderActual.getId());
    }

    @Test
    @DisplayName("testDelete")
    void testDelete() {
        Optional<PurchaseOrder> purchaseOrderDelete = purchaseOrderTest.findById(1L);
        Assertions.assertTrue(purchaseOrderDelete.isPresent());

        purchaseOrderTest.delete(purchaseOrderDelete.get());
        Optional<PurchaseOrder> purchaseOrderActual = purchaseOrderTest.findById(1L);
        Assertions.assertFalse(purchaseOrderDelete.isPresent());
    }
}