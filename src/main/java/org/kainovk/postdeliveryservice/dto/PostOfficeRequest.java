package org.kainovk.postdeliveryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kainovk.postdeliveryservice.model.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOfficeRequest {

    private String index;
    private String name;
    private String recipientAddress;
    private Status status;
    private Long mailItemId;
}
