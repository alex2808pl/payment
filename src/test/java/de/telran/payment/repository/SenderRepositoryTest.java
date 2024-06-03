package de.telran.payment.repository;

import de.telran.payment.entity.Sender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Optional;

@DataJpaTest
class SenderRepositoryTest {
    @Autowired
    private SenderRepository senderTest;

@Test
    @DisplayName("Тест на получение объекта")
    void testGet(){
    Sender senderExpected = new Sender();
    senderExpected.setId(1L);
    Optional<Sender> senderActual = senderTest.findById(1L);
    Assertions.assertTrue(senderActual.isPresent());
    Assertions.assertEquals(senderExpected.getId(),senderActual.get().getId());
}
    @Test
    @DisplayName("Тест на создание объекта")
    void testInsert(){
        Sender senderExpected = new Sender();

        senderExpected.setCard("12345");
        senderExpected.setIban("54321");
        senderExpected.setName("Marta");
        senderExpected.setPaypalId("253535hshsgh");
        senderExpected.setCreatedAt(Timestamp.valueOf("2021-01-01 00:00:02.94001"));
        senderExpected.setUpdatedAt(Timestamp.valueOf("2022-01-01 01:00:22.94222"));

        Sender senderActual = senderTest.save(senderExpected);

        Assertions.assertNotNull(senderActual);
        Assertions.assertTrue(senderExpected.getId() > 0 );
        Assertions.assertEquals(senderExpected.getId(),senderActual.getId());
    }
    @Test
    @DisplayName("Тест на редакцию плательщика")
    void testEdit(){
    Optional<Sender> senderDb = senderTest.findById(2L);
    Assertions.assertTrue(senderDb.isPresent());
        System.out.println(senderDb.get().getId());

        Sender senderExpected = senderDb.get();
        senderExpected.setId(3L);
        System.out.println(senderExpected.getId());

        Sender senderActual = senderTest.save(senderExpected);
        Assertions.assertNotNull(senderActual);
        Assertions.assertEquals(senderExpected.getId(),senderActual.getId());
        System.out.println(senderActual.getId());
    }
    @Test
    @DisplayName("Тест на удаление синглтона")
    void testDelete(){
    Optional<Sender> senderDelete = senderTest.findById(3L);
    Assertions.assertTrue(senderDelete.isPresent());

    senderTest.delete(senderDelete.get());
    Optional<Sender> senderActual = senderTest.findById(3L);
    Assertions.assertFalse(senderActual.isPresent());

    }
}