package de.telran.payment.repository;

import de.telran.payment.entity.Recipient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Optional;

@DataJpaTest
class RecipientRepositoryTest {
    @Autowired
    private RecipientRepository recipientTest;

    @Test
    @DisplayName("Тест на получение синглтона с базы данных")
    void testGet(){
        Recipient recipientExpected = new Recipient();
        recipientExpected.setId(1L);

        Optional<Recipient> recipientActual = recipientTest.findById(1L);
        Assertions.assertTrue(recipientActual.isPresent());
        Assertions.assertEquals(recipientExpected.getId(),recipientActual.get().getId());
        System.out.println(recipientExpected.getId());
        System.out.println(recipientActual.get().getId());
    }
    @Test
    @DisplayName("Тест на создание объекта")
    void testInsert(){
        Recipient recipientExpected = new Recipient();

        recipientExpected.setCard("12345678");
        recipientExpected.setIban("87654321");
        recipientExpected.setName("Shop");
        recipientExpected.setPaypalId("jfdhdh273635");
        recipientExpected.setCreatedAt(Timestamp.valueOf("2021-01-09 14:00:02.94001"));
        recipientExpected.setUpdatedAt(Timestamp.valueOf("2022-02-09 14:00:22.94222"));

        Recipient recipientActual = recipientTest.save(recipientExpected);

        Assertions.assertNotNull(recipientActual);
        Assertions.assertTrue(recipientExpected.getId() > 0 );
        Assertions.assertEquals(recipientExpected.getId(),recipientActual.getId());
    }
    
    @Test
    @DisplayName("Тест на редакцию получателя платежа")
    void testEdit(){
        Optional<Recipient> recipientDb = recipientTest.findById(2L);
        Assertions.assertTrue(recipientDb.isPresent());
        System.out.println(recipientDb.get().getId());

        Recipient RecipientExpected = recipientDb.get();
        RecipientExpected.setId(3L);
        System.out.println(RecipientExpected.getId());

        Recipient RecipientActual = recipientTest.save(RecipientExpected);
        Assertions.assertNotNull(RecipientActual);
        Assertions.assertEquals(RecipientExpected.getId(),RecipientActual.getId());
        System.out.println(RecipientActual.getId());
    }
    @Test
    @DisplayName("Тест на удаление POJO")
    void testDelete(){
        Optional<Recipient> RecipientDelete = recipientTest.findById(3L);
        Assertions.assertTrue(RecipientDelete.isPresent());

        recipientTest.delete(RecipientDelete.get());
        Optional<Recipient> RecipientActual = recipientTest.findById(3L);
        Assertions.assertFalse(RecipientActual.isPresent());
    }
}