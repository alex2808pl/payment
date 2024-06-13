package de.telran.payment.service;

import de.telran.payment.dto.SenderDto;
import de.telran.payment.entity.Sender;
import de.telran.payment.mapper.Mappers;
import de.telran.payment.repository.SenderRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SenderServiceTest {
    @Mock
    private SenderRepository senderRepositoryMock;

    @Mock
    private Mappers mappersMock;

    @InjectMocks
    private SenderService senderServiceTest;

    private SenderDto senderDtoExpected1;
    private SenderDto senderDtoExpected2;

    private Sender senderEntityExpected1;
    private Sender senderEntityExpected2;

    @BeforeEach
    void setUp() {
        senderEntityExpected1 = new Sender(1L, "Beam", "ENG7654321", "987654321", "ox12345678",
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), null
        );
        senderEntityExpected2 = new Sender(2L, "Bill", "IT0654321", "123456789", "oy00345678",
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), null
        );
        senderDtoExpected1 = SenderDto.builder()
                .id(1L)
                .card("987654321")
                .iban("ENG7654321")
                .paypalId("ox12345678")
                .name("Beam")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        senderDtoExpected2 = SenderDto.builder()
                .id(2L)
                .card("123456789")
                .iban("IT0654321")
                .paypalId("oy00345678")
                .name("Bill")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSenderTest() {
        when(senderRepositoryMock.findAll()).thenReturn(List.of(senderEntityExpected1, senderEntityExpected2));
        when(mappersMock.convertToSenderDto(senderEntityExpected1)).thenReturn(senderDtoExpected1);
        when(mappersMock.convertToSenderDto(senderEntityExpected2)).thenReturn(senderDtoExpected2);

        List<SenderDto> actualSenderDtoList = senderServiceTest.getSender();

        assertTrue(actualSenderDtoList.size() > 0);
        verify(mappersMock, times(2)).convertToSenderDto(any(Sender.class));
        assertEquals(senderDtoExpected1.getId(), actualSenderDtoList.get(0).getId());
    }

    @Test
    void getSenderById() {
    }

    @Test
    void deleteSenderById() {
    }

    @Test
    void insertSenderTest() {
        when(senderRepositoryMock.save(any(Sender.class))).thenReturn(senderEntityExpected1);
        when(mappersMock.convertToSender(any(SenderDto.class))).thenReturn(senderEntityExpected1);
        when(mappersMock.convertToSenderDto(any(Sender.class))).thenReturn(senderDtoExpected1);

        SenderDto senderDtoActual = senderServiceTest.insertSender(senderDtoExpected1);

        assertNotNull(senderDtoActual.getId());
        assertEquals(senderDtoExpected1.getName(), senderDtoActual.getName());

    }

    @Test
    void updateSender() {
    }
}