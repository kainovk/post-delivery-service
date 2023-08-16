package org.kainovk.postdeliveryservice.service;

import org.junit.jupiter.api.Test;
import org.kainovk.postdeliveryservice.dto.MailItemDto;
import org.kainovk.postdeliveryservice.dto.MailItemRequest;
import org.kainovk.postdeliveryservice.exception.NotFoundException;
import org.kainovk.postdeliveryservice.model.MailItem;
import org.kainovk.postdeliveryservice.model.Type;
import org.kainovk.postdeliveryservice.repository.MailItemRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MailItemServiceTest {

    @InjectMocks
    private MailItemService mailItemService;

    @Mock
    private MailItemRepository mailItemRepository;

    @Test
    void testCreateMailItem() {
        MailItemRequest request = prepareValidMailItemRequest();
        MailItem mailItem = prepareValidMailItem();

        when(mailItemRepository.save(any(MailItem.class))).thenReturn(mailItem);

        assertDoesNotThrow(() -> mailItemService.createMailItem(request));

        verify(mailItemRepository, times(1)).save(any(MailItem.class));
    }

    @Test
    void testGetMailItemsByRecipientName() {
        String recipientName = "Some Name";
        List<MailItem> mailItemList = new ArrayList<>();

        when(mailItemRepository.findAllByRecipientName(recipientName)).thenReturn(mailItemList);

        List<MailItemDto> result = mailItemService.getMailItemsByRecipientName(recipientName);

        assertEquals(mailItemList.size(), result.size());
        verify(mailItemRepository, times(1)).findAllByRecipientName(recipientName);
    }

    @Test
    void testGetMailItemStatus() {
        Long id = 1L;
        MailItem mailItem = prepareValidMailItem();

        when(mailItemRepository.findById(id)).thenReturn(Optional.of(mailItem));

        String status = mailItemService.getMailItemStatus(id);

        assertNotNull(status);
        verify(mailItemRepository, times(1)).findById(id);
    }

    @Test
    void testGetMailItemStatusNotFound() {
        Long id = 1L;

        when(mailItemRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> mailItemService.getMailItemStatus(id));

        verify(mailItemRepository, times(1)).findById(id);
    }

    private MailItemRequest prepareValidMailItemRequest() {
        return new MailItemRequest(
                Type.LETTER,
                "111-222",
                "address",
                "name"
        );
    }

    private MailItem prepareValidMailItem() {
        return new MailItem(
                Type.LETTER,
                "111-222",
                "address",
                "name",
                LocalDateTime.now(),
                new ArrayList<>()
        );
    }
}