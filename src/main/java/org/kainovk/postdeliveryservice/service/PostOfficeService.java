package org.kainovk.postdeliveryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kainovk.postdeliveryservice.dto.PostOfficeRequest;
import org.kainovk.postdeliveryservice.exception.NotFoundException;
import org.kainovk.postdeliveryservice.mapper.PostOfficeMapper;
import org.kainovk.postdeliveryservice.model.MailItem;
import org.kainovk.postdeliveryservice.model.PostOffice;
import org.kainovk.postdeliveryservice.repository.PostOfficeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostOfficeService {

    private final PostOfficeRepository postOfficeRepository;
    private final MailItemService mailItemService;

    public void createPostOffice(PostOfficeRequest request) {
        Long mailItemId = request.getMailItemId();
        MailItem mailItem = mailItemService.findById(mailItemId);

        PostOffice postOffice = postOfficeRepository.save(PostOfficeMapper.toEntity(request, mailItem));
        log.info("Post Office {} successfully created", postOffice);
    }

    public void departure(Long id) {
        PostOffice postOffice = findById(id);

        if (postOffice.getDepartureTime() != null) {
            return;
        }

        postOffice.setDepartureTime(LocalDateTime.now());
        postOfficeRepository.save(postOffice);
        log.info("Departure time of {} is updated", postOffice);
    }

    private PostOffice findById(Long id) {
        return postOfficeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Post Office not found by id " + id)
        );
    }
}
