package dino.junction.common.model;

import com.google.api.gax.rpc.StatusCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
public class CustomException extends RuntimeException {

    private static final String EXCEPTION_INFO_BRACKET = "{ %s | %s }";
    private static final String CODE_MESSAGE = " Code: %d, Message: %s ";
    private static final String PROPERTY_VALUE = "%s=%s";
    private static final String VALUE_DELIMITER = "; ";
    private static final String RESPONSE_MESSAGE = "%d %s";

    private final int status;
    private final ErrorCode code;
    private final String message;
    private final Time time;

    public CustomException(int status, ErrorCode code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.time = new Time(System.currentTimeMillis());
    }
}
