package org.kainovk.postdeliveryservice.service;

import org.junit.jupiter.api.Test;
import org.kainovk.postdeliveryservice.dto.PostOfficeRequest;
import org.kainovk.postdeliveryservice.model.MailItem;
import org.kainovk.postdeliveryservice.model.PostOffice;
import org.kainovk.postdeliveryservice.model.Status;
import org.kainovk.postdeliveryservice.repository.PostOfficeRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostOfficeServiceTest {

    @InjectMocks
    private PostOfficeService postOfficeService;

    @Mock
    private PostOfficeRepository postOfficeRepository;

    @Mock
    private MailItemService mailItemService;

    @Test
    void testCreatePostOffice() {
        PostOfficeRequest request = prepareValidPostOfficeRequest();
        Long mailItemId = request.getMailItemId();
        MailItem mailItem = new MailItem();
        PostOffice postOffice = prepareValidPostOffice();

        when(mailItemService.findById(mailItemId)).thenReturn(mailItem);
        when(postOfficeRepository.save(any(PostOffice.class))).thenReturn(postOffice);

        assertDoesNotThrow(() -> postOfficeService.createPostOffice(request));

        verify(mailItemService, times(1)).findById(mailItemId);
        verify(postOfficeRepository, times(1)).save(any(PostOffice.class));
    }

    @Test
    void testDeparture() {
        Long id = 1L;
        PostOffice postOffice = prepareValidPostOffice();

        when(postOfficeRepository.findById(id)).thenReturn(Optional.of(postOffice));

        postOfficeService.departure(id);

        assertNotNull(postOffice.getDepartureTime());
        verify(postOfficeRepository, times(1)).findById(id);
        verify(postOfficeRepository, times(1)).save(postOffice);
    }

    @Test
    void testDepartureAlreadyDeparted() {
        Long id = 1L;
        PostOffice postOffice = prepareValidPostOffice();
        LocalDateTime departureTime = LocalDateTime.now();
        postOffice.setDepartureTime(departureTime);

        when(postOfficeRepository.findById(id)).thenReturn(Optional.of(postOffice));

        postOfficeService.departure(id);

        assertEquals(postOffice.getDepartureTime(), departureTime);
        verify(postOfficeRepository, times(1)).findById(id);
        verify(postOfficeRepository, never()).save(postOffice);
    }

    private PostOffice prepareValidPostOffice() {
        return new PostOffice(
                "123-321",
                "post office 1",
                "post office 2",
                Status.IN_PROGRESS,
                LocalDateTime.now().minusDays(1),
                new MailItem()
        );
    }

    private PostOfficeRequest prepareValidPostOfficeRequest() {
        return new PostOfficeRequest(
                "123-321",
                "post office 1",
                "post office 2",
                Status.IN_PROGRESS,
                1L
        );
    }
}