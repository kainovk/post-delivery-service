package org.kainovk.postdeliveryservice.service;

import lombok.RequiredArgsConstructor;
import org.kainovk.postdeliveryservice.repository.PostOfficeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostOfficeService {

    private final PostOfficeRepository postOfficeRepository;
}
