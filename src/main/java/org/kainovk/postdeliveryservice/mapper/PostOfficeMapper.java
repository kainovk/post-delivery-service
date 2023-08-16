package org.kainovk.postdeliveryservice.mapper;

import org.kainovk.postdeliveryservice.dto.PostOfficeDto;
import org.kainovk.postdeliveryservice.model.PostOffice;

public class PostOfficeMapper {

    public static PostOfficeDto toDto(PostOffice postOffice) {
        return PostOfficeDto.builder()
                .id(postOffice.getId())
                .index(postOffice.getIndex())
                .name(postOffice.getName())
                .recipientAddress(postOffice.getRecipientAddress())
                .status(postOffice.getStatus().name())
                .timestamp(postOffice.getTimestamp())
                .build();
    }
}
