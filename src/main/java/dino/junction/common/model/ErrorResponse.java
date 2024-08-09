package dino.junction.common.model;

import dino.junction.common.error.CustomException;
import dino.junction.common.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@Getter
public class ErrorResponse {
    private final int status;
    private final ErrorCode code;
    private final String message;
    private final String traceId;
    private final Date timestamp;

    public ErrorResponse(int status, ErrorCode code, String message, String traceId) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.traceId = traceId;
        this.timestamp = new Date();
    }

    public static ResponseEntity<ErrorResponse> from(final CustomException customException, String logTrace) {
        return ResponseEntity.status(customException.getStatus()).body(new ErrorResponse(customException.getStatus(), customException.getCode(), customException.getMessage(), logTrace));
    }
}
