package de.telran.payment.service;

import de.telran.payment.dto.RecipientDto;
import de.telran.payment.entity.Recipient;
import de.telran.payment.mapper.Mappers;
import de.telran.payment.repository.RecipientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipientServiceTest {
    @Mock
    private  RecipientRepository recipientRepositoryMock;

    @Mock
    private  Mappers mappersMock;

    @InjectMocks
    private RecipientService recipientServiceTest;


    private RecipientDto recipientDtoExpected1;
    private RecipientDto recipientDtoExpected2;

    private Recipient recipientEntityExpected1;
    private Recipient recipientEntityExpected2;


    @BeforeEach
    void setUp() {

        recipientEntityExpected1 = new Recipient(
                1L, "TestName1", "DE1234567890", "1234567890", "PI1234567890",
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), null
        );

        recipientEntityExpected2 = new Recipient(
                2L, "TestName2", "DE0034567890", "0034567890", "PI0034567890",
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), null
        );

        recipientDtoExpected1 = RecipientDto.builder()
                .id(1L)
                .card("1234567890")
                .iban("DE1234567890")
                .paypalId("PI1234567890")
                .name("TestName1")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        recipientDtoExpected2 = RecipientDto.builder()
                .id(2L)
                .card("0034567890")
                .iban("DE0034567890")
                .paypalId("PI0034567890")
                .name("TestName2")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

    }

    @AfterEach
    void tearDown() {
     }

    @Test
    void getRecipientTest() {
        when(recipientRepositoryMock.findAll()).thenReturn(List.of(recipientEntityExpected1, recipientEntityExpected2));

        when(mappersMock.convertToRecipientDto(recipientEntityExpected1)).thenReturn(recipientDtoExpected1);
        when(mappersMock.convertToRecipientDto(recipientEntityExpected2)).thenReturn(recipientDtoExpected2);

        List<RecipientDto> actualRecipientDtoList = recipientServiceTest.getRecipient();

        // Самостоятельно определяте набор проверок
        assertTrue(actualRecipientDtoList.size()>0);
        verify(mappersMock, times(2)).convertToRecipientDto(any(Recipient.class));
        assertEquals(recipientEntityExpected1.getId(), actualRecipientDtoList.get(0).getId());

    }

    @Test
    void insertRecipientTest() {
        when(recipientRepositoryMock.save(any(Recipient.class))).thenReturn(recipientEntityExpected1);
        when(mappersMock.convertToRecipient(any(RecipientDto.class))).thenReturn(recipientEntityExpected1);
        when(mappersMock.convertToRecipientDto(any(Recipient.class))).thenReturn(recipientDtoExpected1);

        RecipientDto recipientDtoActual = recipientServiceTest.insertRecipient(recipientDtoExpected1);

        assertNotNull(recipientDtoActual.getId());
        assertEquals(recipientDtoExpected1.getName(), recipientDtoActual.getName());
    }
}