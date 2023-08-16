package org.kainovk.postdeliveryservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiException {

    private final String message;
    private final LocalDateTime timestamp;
}
