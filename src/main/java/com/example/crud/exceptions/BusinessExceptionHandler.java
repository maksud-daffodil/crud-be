package com.example.crud.exceptions;

import com.example.crud.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BusinessExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex) {
        BaseResponse<?> response = new BaseResponse<>();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError(ex.getMessage());
        response.setErrorCode(ex.getErrorCode());

        StackTraceElement[] stackTrace = ex.getStackTrace();
        if (stackTrace.length > 0) {
            StackTraceElement origin = stackTrace[0];
            String className = origin.getClassName();
            String methodName = origin.getMethodName();
            int lineNumber = origin.getLineNumber();
            log.error("[Knowledge] : Business Exception in {}.{}():{} - {}",
                    className, methodName, lineNumber, ex.getMessage());
        } else {
            log.error("[Knowledge] : Business Exception: {}", ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleServerErrorException(Exception ex) {
        BaseResponse<?> response = new BaseResponse<>();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setError(ex.getMessage());
        response.setErrorCode("SOMETHING_WENT_WRONG");

        StackTraceElement[] stackTrace = ex.getStackTrace();
        if (stackTrace.length > 0) {
            StackTraceElement origin = stackTrace[0];
            String className = origin.getClassName();
            String methodName = origin.getMethodName();
            int lineNumber = origin.getLineNumber();
            log.error("[Knowledge] : Internal server error in {}.{}():{} - {}",
                    className, methodName, lineNumber, ex.getMessage());
        } else {
            log.error("[Knowledge] : Internal server error: {}", ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
    }
}
