package org.kainovk.postdeliveryservice.controller;

import lombok.RequiredArgsConstructor;
import org.kainovk.postdeliveryservice.service.PostOfficeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-offices")
@RequiredArgsConstructor
public class PostOfficeController {

    private final PostOfficeService postOfficeService;
}
