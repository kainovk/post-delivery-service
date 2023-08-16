package org.kainovk.postdeliveryservice.controller;

import lombok.RequiredArgsConstructor;
import org.kainovk.postdeliveryservice.dto.MailItemDto;
import org.kainovk.postdeliveryservice.dto.MailItemRequest;
import org.kainovk.postdeliveryservice.service.MailItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mail-items")
@RequiredArgsConstructor
public class MailItemController {

    private final MailItemService mailItemService;

    @PostMapping
    public ResponseEntity<?> createMailItem(@RequestBody MailItemRequest mailItem) {
        mailItemService.createMailItem(mailItem);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<MailItemDto> getMailItemsByRecipientName(@RequestParam String recipientName) {
        return mailItemService.getMailItemsByRecipientName(recipientName);
    }
}
