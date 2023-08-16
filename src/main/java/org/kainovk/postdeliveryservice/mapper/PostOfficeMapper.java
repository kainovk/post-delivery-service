package org.kainovk.postdeliveryservice.mapper;

import org.kainovk.postdeliveryservice.dto.PostOfficeDto;
import org.kainovk.postdeliveryservice.dto.PostOfficeRequest;
import org.kainovk.postdeliveryservice.model.MailItem;
import org.kainovk.postdeliveryservice.model.PostOffice;

import java.time.LocalDateTime;

public class PostOfficeMapper {

    public static PostOfficeDto toDto(PostOffice postOffice) {
        return PostOfficeDto.builder()
                .id(postOffice.getId())
                .index(postOffice.getIndex())
                .name(postOffice.getName())
                .recipientAddress(postOffice.getRecipientAddress())
                .status(postOffice.getStatus().name())
                .arrivalTime(postOffice.getArrivalTime())
                .departureTime(postOffice.getDepartureTime())
                .build();
    }

    public static PostOffice toEntity(PostOfficeRequest request, MailItem mailItem) {
        return new PostOffice(
                request.getIndex(),
                request.getName(),
                request.getRecipientAddress(),
                request.getStatus(),
                LocalDateTime.now(),
                mailItem
        );
    }
}
