package org.kainovk.postdeliveryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.kainovk.postdeliveryservice.dto.MailItemDto;
import org.kainovk.postdeliveryservice.dto.MailItemRequest;
import org.kainovk.postdeliveryservice.model.Type;
import org.kainovk.postdeliveryservice.service.MailItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MailItemController.class)
class MailItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailItemService mailItemService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testCreateMailItem() throws Exception {
        MailItemRequest request = prepareValidMailItemRequest();

        mockMvc.perform(post("/api/mail-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(mailItemService, times(1)).createMailItem(any(MailItemRequest.class));
    }

    @Test
    void testGetMailItemsByRecipientName() throws Exception {
        String recipientName = "RecipientName";
        List<MailItemDto> mailItems = List.of(prepareValidMailItemDto());

        when(mailItemService.getMailItemsByRecipientName(recipientName)).thenReturn(mailItems);

        mockMvc.perform(get("/api/mail-items")
                        .param("recipientName", recipientName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(mailItems.size()));

        verify(mailItemService, times(1)).getMailItemsByRecipientName(recipientName);
    }

    @Test
    void testGetMailItemById() throws Exception {
        Long id = 1L;
        MailItemDto mailItemDto = prepareValidMailItemDto();

        when(mailItemService.getMailItemById(id)).thenReturn(mailItemDto);

        mockMvc.perform(get("/api/mail-items/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mailItemDto.getId()));

        verify(mailItemService, times(1)).getMailItemById(id);
    }

    @Test
    void testGetMailItemStatus() throws Exception {
        Long id = 1L;
        String status = "READY_FOR_PICKUP";

        when(mailItemService.getMailItemStatus(id)).thenReturn(status);

        mockMvc.perform(get("/api/mail-items/{id}/status", id))
                .andExpect(status().isOk())
                .andExpect(content().string(status));

        verify(mailItemService, times(1)).getMailItemStatus(id);
    }

    private MailItemRequest prepareValidMailItemRequest() {
        return new MailItemRequest(
                Type.LETTER,
                "111-222",
                "address",
                "name"
        );
    }

    private MailItemDto prepareValidMailItemDto() {
        return MailItemDto.builder()
                .id(1L)
                .type(Type.POSTCARD.name())
                .recipientIndex("321-321")
                .recipientAddress("address")
                .recipientName("Name")
                .createdAt(LocalDateTime.now())
                .build();
    }
}