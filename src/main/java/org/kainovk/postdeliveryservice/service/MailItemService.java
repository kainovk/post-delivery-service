package org.kainovk.postdeliveryservice.service;

import lombok.RequiredArgsConstructor;
import org.kainovk.postdeliveryservice.repository.MailItemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailItemService {

    private final MailItemRepository mailItemRepository;
}
