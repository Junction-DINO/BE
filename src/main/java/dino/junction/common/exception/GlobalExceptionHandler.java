package dino.junction.common.exception;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.gax.rpc.UnauthenticatedException;
import dino.junction.common.logger.aop.LogTrace;
import dino.junction.common.model.CustomException;
import dino.junction.common.model.ErrorCode;
import dino.junction.common.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    public final LogTrace logTrace;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        String combinedErrorMessage = String.join(", ", errorMessages);
        logger.error(String.valueOf(ex.getClass()), ex);
        return ResponseEntity.status(HttpStatusCodes.STATUS_CODE_BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatusCodes.STATUS_CODE_BAD_REQUEST,
                        ErrorCode.BAD_REQUEST,
                        combinedErrorMessage,
                        "[Validation filter]")
                );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        return ErrorResponse.from(e, logTrace.getTraceId());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(final Exception e) {
        return convertErrorResponse(e, HttpStatusCodes.STATUS_CODE_BAD_REQUEST, ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(final Exception e) {
        return convertErrorResponse(e, HttpStatusCodes.STATUS_CODE_UNAUTHORIZED, ErrorCode.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(final Exception e) {
        return convertErrorResponse(e, HttpStatusCodes.STATUS_CODE_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> convertErrorResponse(Exception ex, int status, ErrorCode code) {
        logger.error(String.valueOf(ex.getClass()), ex);
        return ResponseEntity.status(status).body(new ErrorResponse(status, code, ex.getMessage(), logTrace.getTraceId()));
    }
}
