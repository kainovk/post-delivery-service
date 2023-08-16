package org.kainovk.postdeliveryservice.mapper;

import org.kainovk.postdeliveryservice.dto.MailItemDto;
import org.kainovk.postdeliveryservice.dto.MailItemRequest;
import org.kainovk.postdeliveryservice.model.MailItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MailItemsMapper {

    public static MailItemDto toDto(MailItem mailItem) {
        return MailItemDto.builder()
                .id(mailItem.getId())
                .type(mailItem.getType().name())
                .recipientIndex(mailItem.getRecipientIndex())
                .recipientAddress(mailItem.getRecipientAddress())
                .recipientName(mailItem.getRecipientName())
                .createdAt(mailItem.getCreatedAt())
                .postOffices(mailItem.getPostOffices().stream()
                        .map(PostOfficeMapper::toDto)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public static MailItem toEntity(MailItemRequest request) {
        return new MailItem(
                request.getType(),
                request.getRecipientIndex(),
                request.getRecipientAddress(),
                request.getRecipientName(),
                LocalDateTime.now(),
                new ArrayList<>()
        );
    }
}
