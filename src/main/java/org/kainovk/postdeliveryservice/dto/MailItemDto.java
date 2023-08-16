package org.kainovk.postdeliveryservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailItemDto {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String recipientIndex;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String recipientAddress;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String recipientName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime createdAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PostOfficeDto> postOffices;
}
