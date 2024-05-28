package de.telran.payment.repository;

import de.telran.payment.entity.PurchaseOrder;
import de.telran.payment.entity.Sender;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class SenderRepositoryTest {
    @Autowired
    private SenderRepository senderRepositoryTest;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepositoryTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Тест на получение данных")
    void testGet() {
        Sender senderExpected = new Sender(1L,"Andreas",null,null,null,null,null,null);
        Optional<Sender> senderActual = senderRepositoryTest.findById(1L);
        Assertions.assertTrue(senderActual.isPresent());
        Assertions.assertEquals(senderExpected.getId(),senderActual.get().getId());
        Assertions.assertEquals(senderExpected.getName(),senderActual.get().getName());
    }
    @Test
    @DisplayName("Тест на создание объекта")
    void testInsert() {


        Optional<PurchaseOrder> purchaseOrderActualTest = purchaseOrderRepositoryTest.findById(1L);
        Assertions.assertTrue(purchaseOrderActualTest.isPresent());
        Sender senderExpected = new Sender();
        senderExpected.setIban("2345");
        senderExpected.setPurchaseOrder((Set<PurchaseOrder>) purchaseOrderActualTest.get());
      //  senderExpected.setPurchaseOrder(null);

        Sender senderActual = senderRepositoryTest.save(senderExpected);
        Assertions.assertNotNull(senderActual);
        Assertions.assertTrue(senderExpected.getId() > 0);
        Assertions.assertEquals(senderExpected.getPurchaseOrder(),senderActual.getPurchaseOrder());

    }
    @Test
    @DisplayName("Редактирование существующего объекта")
    void testEdit() {
        Optional<Sender> senderDb = senderRepositoryTest.findById(1L); // существующий в базе данных
        Assertions.assertTrue(senderDb.isPresent());

        System.out.println(senderDb.get().getPurchaseOrder());

        Sender senderExpected = senderDb.get();
        senderExpected.setName("Sam");
        Sender senderActual = senderRepositoryTest.save(senderExpected);
        System.out.println(senderActual.getPurchaseOrder());

        Assertions.assertNotNull(senderActual);
        Assertions.assertEquals(senderExpected.getPurchaseOrder(),senderActual.getPurchaseOrder());
        Assertions.assertNotEquals(senderExpected.getPurchaseOrder(),senderActual.getPurchaseOrder());



    }
    @Test
    @DisplayName("Test of deleting")
    void testDelete() {
        Optional<Sender> senderForDelete = senderRepositoryTest.findById(1L); // существующий в базе данных
        Assertions.assertTrue(senderForDelete.isPresent());

        senderRepositoryTest.delete(senderForDelete.get());

        Optional<Sender> senderActual = senderRepositoryTest.findById(1L);
        Assertions.assertFalse(senderForDelete.isPresent());

    }


}