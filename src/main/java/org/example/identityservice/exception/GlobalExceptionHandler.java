package org.example.identityservice.exception;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.example.identityservice.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";
    private static final String MAX_ATTRIBUTE = "max";

    @ExceptionHandler(value = {Exception.class})
    ResponseEntity<ApiResponse<Objects>> handlingRuntimeException(RuntimeException ex) {
        ApiResponse<Objects> apiResponse = ApiResponse.<Objects>builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                .build();

        return ResponseEntity.status(400).body(apiResponse);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    ResponseEntity<ApiResponse<Objects>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String enumKey = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        ErrorCode errorCode;

        Map<String, Object> attributes = null;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
            var constraintViolations = ex.getBindingResult()
                    .getAllErrors().get(0).unwrap(ConstraintViolation.class);

            attributes = constraintViolations.getConstraintDescriptor().getAttributes();

            log.info(attributes.toString());
        } catch (IllegalArgumentException ignored) {
            errorCode = ErrorCode.INVALID_KEY;
        }

        ApiResponse<Objects> apiResponse = ApiResponse.<Objects>builder()
                .code(errorCode.getCode())
                .message(Objects.nonNull(attributes) ? mapAttribute(errorCode.getMessage(), attributes) : errorCode.getMessage())
                .build();

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
//        return ResponseEntity.badRequest().body(apiResponse);

    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = attributes.get(MIN_ATTRIBUTE).toString();
        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue)
                .replace("{" + MAX_ATTRIBUTE + "}", attributes.get(MAX_ATTRIBUTE).toString());
    }

    @ExceptionHandler(value = {AppException.class})
    ResponseEntity<ApiResponse<Objects>> handlingAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        ApiResponse<Objects> apiResponse = ApiResponse.<Objects>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
//        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AuthorizationDeniedException.class})
    public ResponseEntity<ApiResponse<Objects>> handlingAuthorizationDeniedException(AuthorizationDeniedException ex) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        ApiResponse<Objects> apiResponse = ApiResponse.<Objects>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
    }
}
