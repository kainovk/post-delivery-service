package org.kainovk.postdeliveryservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.kainovk.postdeliveryservice.model.Type;

@Data
@NoArgsConstructor
public class MailItemRequest {

    private Type type;
    private String recipientIndex;
    private String recipientAddress;
    private String recipientName;
}
