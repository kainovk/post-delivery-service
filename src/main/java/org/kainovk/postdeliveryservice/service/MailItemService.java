package org.kainovk.postdeliveryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kainovk.postdeliveryservice.dto.MailItemDto;
import org.kainovk.postdeliveryservice.dto.MailItemRequest;
import org.kainovk.postdeliveryservice.exception.NotFoundException;
import org.kainovk.postdeliveryservice.mapper.MailItemsMapper;
import org.kainovk.postdeliveryservice.model.MailItem;
import org.kainovk.postdeliveryservice.model.PostOffice;
import org.kainovk.postdeliveryservice.repository.MailItemRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailItemService {

    private final MailItemRepository mailItemRepository;

    public void createMailItem(MailItemRequest request) {
        MailItem mailItem = mailItemRepository.save(MailItemsMapper.toEntity(request));
        log.info("Mail Item {} successfully created", mailItem);
    }

    public List<MailItemDto> getMailItemsByRecipientName(String recipientName) {
        List<MailItem> mailItems = mailItemRepository.findAllByRecipientName(recipientName);
        log.info("Found {} Mail Items by {} recipient name", mailItems.size(), recipientName);

        return mailItems.stream()
                .map(MailItemsMapper::toDto)
                .collect(Collectors.toList());
    }

    public MailItem findById(Long id) {
        return mailItemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Mail Item does not exist by id " + id)
        );
    }

    public MailItemDto getMailItemById(Long id) {
        MailItem mailItem = findById(id);
        return MailItemsMapper.toDto(mailItem);
    }

    public String getMailItemStatus(Long id) {
        MailItem mailItem = findById(id);
        return mailItem.getPostOffices().stream()
                .sorted(Comparator.comparing(PostOffice::getArrivalTime).reversed())
                .map(postOffice -> postOffice.getStatus().name())
                .findFirst().orElse("Delivery is not started yet");
    }
}
