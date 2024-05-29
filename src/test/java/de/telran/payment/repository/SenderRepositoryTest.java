package de.telran.payment.repository;

import de.telran.payment.entity.PurchaseOrder;
import de.telran.payment.entity.Recipient;
import de.telran.payment.entity.Sender;
import lombok.Data;
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
class SenderRepositoryTest {
@Autowired
private SenderRepository senderTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    @DisplayName("Тест на  получение объекта из базы данных")
    void testGet() {
        Sender senderExpected = new Sender();
        senderExpected.setId(1L);
        Optional<Sender> senderActual = senderTest.findById(1L);
        Assertions.assertTrue(senderActual.isPresent());
        Assertions.assertEquals(senderExpected.getId(), senderActual.get().getId());

    }
    @Test
    void testInsert(){
        Sender  senderExpected = new Sender();

        PurchaseOrder purchaseOrderTest = new PurchaseOrder();
        purchaseOrderTest.setId(1L);
       // senderExpected.setPurchaseOrder(purchaseOrderTest);

        senderExpected.setId(1L);
        senderExpected.setName("Andreas");
        senderExpected.setIban("UA343414452542");
        senderExpected.setPaypalId("341343");
        senderExpected.setCard("321414");
        senderExpected.setCreatedAt(Timestamp.valueOf("2024-05-25 14:33:02.940000"));
        senderExpected.setUpdatedAt(Timestamp.valueOf("2024-05-24 14:33:02.941000"));


        Sender senderActual = senderTest.save(senderExpected);

        Assertions.assertNotNull(senderActual);
        Assertions.assertTrue(senderExpected.getId() > 0);
        Assertions.assertEquals(senderExpected.getId(), senderActual.getId());
    }
    @Test
    void  testEdit(){
        Optional<Sender> senderDb = senderTest.findById(1L);
        Assertions.assertTrue(senderDb.isPresent());
        //System.out.println(senderDb.get().getId());

        Sender senderExpected = senderDb.get();
        senderExpected.setId(1L);

        Sender senderActual = senderTest.save(senderExpected);
        Assertions.assertNotNull(senderActual);
        Assertions.assertEquals(senderExpected.getId(), senderActual.getId());

    }
    @Test
    @DisplayName("Test of deleting")
    void testDelete() {
        Optional<Sender> senderDelete = senderTest.findById(1L);
        Assertions.assertTrue(senderDelete.isPresent());

        senderTest.delete(senderDelete.get());
        Optional<Sender> senderActual = senderTest.findById(1L);
        Assertions.assertFalse(senderActual.isPresent());

    }
}