package org.kainovk.postdeliveryservice.controller;

import lombok.RequiredArgsConstructor;
import org.kainovk.postdeliveryservice.service.MailItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail-items")
@RequiredArgsConstructor
public class MailItemController {

    private final MailItemService mailItemService;
}
