package org.kainovk.postdeliveryservice.controller;

import lombok.RequiredArgsConstructor;
import org.kainovk.postdeliveryservice.dto.PostOfficeRequest;
import org.kainovk.postdeliveryservice.service.PostOfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-offices")
@RequiredArgsConstructor
public class PostOfficeController {

    private final PostOfficeService postOfficeService;

    @PostMapping
    public ResponseEntity<?> createPostOffice(@RequestBody PostOfficeRequest postOffice) {
        postOfficeService.createPostOffice(postOffice);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}/departure")
    public void departure(@PathVariable Long id) {
        postOfficeService.departure(id);
    }
}
