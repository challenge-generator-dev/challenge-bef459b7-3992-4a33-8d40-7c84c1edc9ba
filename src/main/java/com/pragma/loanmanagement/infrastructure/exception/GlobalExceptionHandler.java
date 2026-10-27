package com.pragma.loanmanagement.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFoundException(
            LoanNotFoundException ex, WebRequest request) {
        logger.warn("Préstamo no encontrado: {} - Path: {}", 
                ex.getLoanId(), 
                request.getDescription(false));
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .path(request.getDescription(false).replace("uri=", ""))
                .loanId(ex.getLoanId() != null ? ex.getLoanId().toString() : null)
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación en solicitud: {} - Path: {}", 
                ex.getMessage(), 
                request.getDescription(false));
        
        List<FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldError(
                        error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue() != null ? error.getRejectedValue().toString() : null))
                .collect(Collectors.toList());
        
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Error en la validación de campos")
                .fieldErrors(fieldErrors)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        logger.warn("Tipo de argumento inválido: {} - Path: {}", 
                ex.getMessage(), 
                request.getDescription(false));
        
        String message = String.format("El parámetro '%s' tiene un valor inválido: '%s'",
                ex.getName(), ex.getValue());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(message)
                .errorCode("TYPE_MISMATCH")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento ilegal: {} - Path: {}", 
                ex.getMessage(), 
                request.getDescription(false));
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .errorCode("ILLEGAL_ARGUMENT")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: {} - Path: {}", 
                ex.getMessage(), 
                request.getDescription(false), 
                ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Ha ocurrido un error inesperado. Por favor, contacte al administrador.")
                .errorCode("INTERNAL_ERROR")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ErrorResponse {
        private String timestamp;
        private int status;
        private String error;
        private String message;
        private String errorCode;
        private String path;
        private String loanId;
        private Map<String, Object> additionalInfo;

        private ErrorResponse() {}

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getErrorCode() { return errorCode; }
        public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public String getLoanId() { return loanId; }
        public void setLoanId(String loanId) { this.loanId = loanId; }
        public Map<String, Object> getAdditionalInfo() { return additionalInfo; }
        public void setAdditionalInfo(Map<String, Object> additionalInfo) { this.additionalInfo = additionalInfo; }

        public static class ErrorResponseBuilder {
            private String timestamp;
            private int status;
            private String error;
            private String message;
            private String errorCode;
            private String path;
            private String loanId;
            private Map<String, Object> additionalInfo = new HashMap<>();

            public ErrorResponseBuilder timestamp(String timestamp) { this.timestamp = timestamp; return this; }
            public ErrorResponseBuilder status(int status) { this.status = status; return this; }
            public ErrorResponseBuilder error(String error) { this.error = error; return this; }
            public ErrorResponseBuilder message(String message) { this.message = message; return this; }
            public ErrorResponseBuilder errorCode(String errorCode) { this.errorCode = errorCode; return this; }
            public ErrorResponseBuilder path(String path) { this.path = path; return this; }
            public ErrorResponseBuilder loanId(String loanId) { this.loanId = loanId; return this; }
            public ErrorResponseBuilder additionalInfo(String key, Object value) { this.additionalInfo.put(key, value); return this; }
            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.errorCode = this.errorCode;
                response.path = this.path;
                response.loanId = this.loanId;
                response.additionalInfo = this.additionalInfo;
                return response;
            }
        }
    }

    public static class ValidationErrorResponse {
        private String timestamp;
        private int status;
        private String error;
        private String message;
        private List<FieldError> fieldErrors;
        private String path;

        private ValidationErrorResponse() {}

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public List<FieldError> getFieldErrors() { return fieldErrors; }
        public void setFieldErrors(List<FieldError> fieldErrors) { this.fieldErrors = fieldErrors; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public static class ValidationErrorResponseBuilder {
            private String timestamp;
            private int status;
            private String error;
            private String message;
            private List<FieldError> fieldErrors;
            private String path;

            public ValidationErrorResponseBuilder timestamp(String timestamp) { this.timestamp = timestamp; return this; }
            public ValidationErrorResponseBuilder status(int status) { this.status = status; return this; }
            public ValidationErrorResponseBuilder error(String error) { this.error = error; return this; }
            public ValidationErrorResponseBuilder message(String message) { this.message = message; return this; }
            public ValidationErrorResponseBuilder fieldErrors(List<FieldError> fieldErrors) { this.fieldErrors = fieldErrors; return this; }
            public ValidationErrorResponseBuilder path(String path) { this.path = path; return this; }
            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.fieldErrors = this.fieldErrors;
                response.path = this.path;
                return response;
            }
        }
    }

    public static class FieldError {
        private String field;
        private String message;
        private String rejectedValue;

        public FieldError(String field, String message, String rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }

        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getRejectedValue() { return rejectedValue; }
        public void setRejectedValue(String rejectedValue) { this.rejectedValue = rejectedValue; }
    }
}